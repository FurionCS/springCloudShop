package com.spring.service;

import com.spring.domain.model.Order;

import java.util.List;

/**
 * @Description 订单service接口
 * @Author ErnestCheng
 * @Date 2017/5/26.
 */
public interface OrderService {
    /**
     * 通过用户id获得用户订单列表
     * @param userId
     * @return
     */
    List<Order> listOrderByUserId(Integer userId);
}
