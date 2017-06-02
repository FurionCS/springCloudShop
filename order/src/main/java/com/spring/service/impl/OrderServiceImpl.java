package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.spring.common.model.StatusCode;
import com.spring.common.model.exception.GlobalException;
import com.spring.domain.model.*;
import com.spring.domain.request.*;
import com.spring.domain.response.ObjectDataResponse;
import com.spring.domain.response.ReservationResponse;
import com.spring.domain.type.OrderStatus;
import com.spring.exception.PartialConfirmException;
import com.spring.exception.ReservationExpireException;
import com.spring.persistence.OrderMapper;
import com.spring.persistence.OrderParticipantMapper;
import com.spring.service.OrderService;
import com.spring.web.client.ProductClient;
import com.spring.web.client.TccClient;
import com.spring.web.client.UserClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

/**
 * @Description 订单service实现
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Service
public class OrderServiceImpl implements OrderService{

    Logger logger= Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderParticipantMapper orderParticipantMapper;
    @Autowired
    private UserClient userClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private TccClient tccClient;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ObjectDataResponse<Order> placeOrder(PlaceOrderRequest request){
        Preconditions.checkNotNull(request);
        final Integer userId= Preconditions.checkNotNull(request.getUserId());
        final Integer productId=Preconditions.checkNotNull(request.getProductId());
        //获取产品
        final Product product=findRemoteProduct(productId);
        //查询用户
        final User user=findRemoteUser(userId);
        //检查余额
        if(user.getBalance()-product.getPrice()<0){
            // 抛出异常
            throw new GlobalException("余额不足", StatusCode.Data_Error);
        }
        //构建订单
        Order order=new Order();
        order.setPrice(product.getPrice());
        order.setProductId(productId);
        order.setUserId(userId);
        order.setStatus(OrderStatus.PROCESSING);
        order.setCreateTime(new Date());
        Date startDay=new Date();
        startDay.setTime(0);
        order.setUpdateTime(startDay);
        order.setDeleteTime(startDay);
        orderMapper.addOrder(order);
        //预留余额
        reserveBalanceAndPersistParticipant(order);
        //预留库存
        reserveProductAndPersistParticipant(order);

        return new ObjectDataResponse<>(order);
    }

    /**
     * 提交资源，确认订单，第一个c
     * @param request
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ObjectDataResponse<Order> confirm(PaymentRequest request) {
        Preconditions.checkNotNull(request);
        Integer orderId=request.getOrderId();
        //检查订单是否存在
        Order order=orderMapper.getOrderById(orderId);
        if(order==null){
            throw new GlobalException("订单不存在",StatusCode.Data_Not_Exist);
        }
        List<OrderParticipant> lop=orderParticipantMapper.listOrderParticipantByOrderId(orderId);
        if(lop.isEmpty()){
            throw new GlobalException("没有预留资源",StatusCode.Data_Not_Exist);
        }
        if(order.getStatus()==OrderStatus.PROCESSING){
            confirmPhase(order,lop);
        }
        return new ObjectDataResponse<>(order);
    }

    @Override
    public List<Order> listOrder() {
        return orderMapper.listOrder();
    }


    /**
     * 调用产品项目获得产品
     * @param productId
     * @return
     */
    private Product findRemoteProduct(Integer productId) {
        Preconditions.checkNotNull(productId);
        ObjectDataResponse<Product> objectDataResponse= productClient.getProductById(productId);
        if(objectDataResponse.getCode()!=StatusCode.Success){
            throw new GlobalException(objectDataResponse.getMessage(),objectDataResponse.getCode());
        }
        final Product product=objectDataResponse.getData();
        if(product==null){
            // 抛出异常
            throw new GlobalException("产品不存在", StatusCode.Data_Error);
        }
        //检查库存
        if(product.getStock()<1){
            // 抛出异常
            throw new GlobalException("库存不足", StatusCode.Data_Error);
        }
        return product;
    }

    /**
     * 调用用户项目中获得用户
     * @param userId
     * @return
     */
    private User findRemoteUser(Integer userId){
        Preconditions.checkNotNull(userId);
        ObjectDataResponse<User> objectDataResponse=userClient.getUserById(userId);
        if(objectDataResponse.getCode()!=StatusCode.Success){
            throw new GlobalException(objectDataResponse.getMessage(),objectDataResponse.getCode());
        }
        final User user=objectDataResponse.getData();
        if(user==null){
            // 抛出异常
            throw new GlobalException("用户不存在", StatusCode.Data_Error);
        }
        //检查余额
        if(user.getBalance()<=0){
            //余额不足
            throw new GlobalException("余额不足", StatusCode.Data_Error);
        }
        return user;
    }

    /**
     * 预留库存
     * @param order
     */
    private void reserveProductAndPersistParticipant(Order order){
        Preconditions.checkNotNull(order);
        StockReservationRequest stockReservationRequest=new StockReservationRequest();
        stockReservationRequest.setProductId(order.getProductId());
        //这里写死一个产品
        stockReservationRequest.setNum(1);
        ReservationResponse reservationResponse=productClient.reserve(stockReservationRequest);
        if(reservationResponse.getParticipantLink()==null){
            throw new GlobalException(reservationResponse.getMessage(),reservationResponse.getCode());
        }
        persistParticipant( reservationResponse.getParticipantLink(),  order.getId());
    }

    /**
     * 预留余额
     * @param order
     */
    private void reserveBalanceAndPersistParticipant(Order order){
        Preconditions.checkNotNull(order);
        BalanceReservationRequest balanceReservationRequest=new BalanceReservationRequest();
        balanceReservationRequest.setUserId(order.getUserId());
        balanceReservationRequest.setAmount(order.getPrice());
        ReservationResponse reservationResponse=userClient.reserve(balanceReservationRequest);
        if(reservationResponse.getParticipantLink()==null){
            throw new GlobalException(reservationResponse.getMessage(),reservationResponse.getCode());
        }
        //添加资源信息
        persistParticipant(reservationResponse.getParticipantLink(), order.getId());
    }
    private void persistParticipant(Participant participant, Integer orderId) {
        Preconditions.checkNotNull(participant);
        Preconditions.checkNotNull(orderId);
        OrderParticipant orderParticipant=new OrderParticipant();
        orderParticipant.setOrderId(orderId);
        orderParticipant.setUri(participant.getUri());
        orderParticipant.setExpireTime(participant.getExpireTime());
        orderParticipant.setCreateTime(OffsetDateTime.now());
        OffsetDateTime defaultDateTime=OffsetDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(8));
        orderParticipant.setUpdateTime(defaultDateTime);
        orderParticipant.setDeleteTime(defaultDateTime);
        orderParticipantMapper.addOrderParticipant(orderParticipant);
    }

    /**
     * 确认资源
     * @param order
     * @param lop
     */
    private void confirmPhase(Order order,List<OrderParticipant> lop){
        ImmutableList<OrderParticipant> links=ImmutableList.copyOf(lop);
        TccRequest tccRequest=new TccRequest(links);
        try{
            tccClient.confirm(tccRequest);
            order.setStatus(OrderStatus.DONE);
            orderMapper.updateOrder(order);
        }catch (HystrixRuntimeException e){
            final Class<? extends Throwable> exceptionCause = e.getCause().getClass();
            if (ReservationExpireException.class.isAssignableFrom(exceptionCause)) {
                // 全部确认预留超时
                order.setStatus(OrderStatus.TIMEOUT);
                orderMapper.updateOrder(order);
            } else if (PartialConfirmException.class.isAssignableFrom(exceptionCause)) {
                order.setStatus(OrderStatus.CONFLICT);
                orderMapper.updateOrder(order);
                markdownConfliction(order, e);
            } else {
                throw e;
            }
        }
    }
    private void markdownConfliction(Order order, HystrixRuntimeException e) {
        Preconditions.checkNotNull(order);
        Preconditions.checkNotNull(e);
        final String message = e.getCause().getMessage();
        logger.error("order id "+order.getId()+" has come across an confliction. "+ message);
        //TODO 错误信息保存到mongodb
    }
}
