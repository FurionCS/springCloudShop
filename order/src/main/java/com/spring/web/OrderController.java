package com.spring.web;

import com.spring.common.model.StatusCode;
import com.spring.domain.model.Order;
import com.spring.domain.model.response.OrderResponse;
import com.spring.service.OrderService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 订单控制器
 * @Author ErnestCheng
 * @Date 2017/5/26.
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value="/listOrderByUserId/{userId}")
    public OrderResponse listOrderByUserId(@PathVariable Integer userId){
        OrderResponse orderResponse=new OrderResponse();
        if(userId==null||userId<1){
            orderResponse.setCode(StatusCode.Fail_Code);
            orderResponse.setMessage("用户id不正确");
        }
        List<Order> lo=orderService.listOrderByUserId(userId);
        orderResponse.setData(lo);
        return orderResponse;
    }
}
