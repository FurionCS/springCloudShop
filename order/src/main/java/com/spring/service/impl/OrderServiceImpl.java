package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.common.model.StatusCode;
import com.spring.common.model.exception.GlobalException;
import com.spring.domain.model.Order;
import com.spring.domain.model.Product;
import com.spring.domain.model.User;
import com.spring.domain.request.PlaceOrderRequest;
import com.spring.domain.response.ObjectDataResponse;
import com.spring.domain.type.OrderStatus;
import com.spring.persistence.OrderMapper;
import com.spring.service.OrderService;
import com.spring.web.client.ProductClient;
import com.spring.web.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description 订单service实现
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserClient userClient;

    @Autowired
    private ProductClient productClient;
    @Override
    public ObjectDataResponse<Order> placeOrder(PlaceOrderRequest request) {
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
        //预留库存

        //预留资源
        return null;
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
    private Product findRemoteProduct(Integer productId){
        Preconditions.checkNotNull(productId);
        final Product product=productClient.getProductById(productId).getData();
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
        final User user=userClient.getUserById(userId).getData();
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
     * 预留余额
     * @param order
     */
    private void reserveProductAndPersistParticipant(Order order){

    }
}
