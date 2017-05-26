package com.spring.service.impl;

import com.spring.domain.mapper.OrderMapper;
import com.spring.domain.model.Order;
import com.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 订单service实现
 * @Author ErnestCheng
 * @Date 2017/5/26.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<Order> listOrderByUserId(Integer userId) {
        //先从缓存中获得，如果有就返回没有就从数据库查询

        //查询数据库
        List<Order> lo= orderMapper.listOrderByUserId(userId);
            //插入到缓存中

        return lo;
    }
}
