package com.spring.service;

import com.spring.domain.model.Order;
import com.spring.domain.request.PaymentRequest;
import com.spring.domain.request.PlaceOrderRequest;
import com.spring.domain.response.ObjectDataResponse;

import java.util.List;

/**
 * @Description 订单service接口
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
public interface OrderService {
    /**
     * 获得所有订单
     * @return
     */
    List<Order> listOrder();
    /**
     * 生成预订单
     * @param request
     * @return
     */
    ObjectDataResponse<Order> placeOrder(PlaceOrderRequest request);

    /**
     * 下单
     * @param request
     * @return
     */
    ObjectDataResponse<Order> confirm(PaymentRequest request);
}
