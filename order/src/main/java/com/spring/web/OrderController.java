package com.spring.web;

import com.spring.common.model.StatusCode;
import com.spring.domain.model.Order;
import com.spring.domain.model.request.UserOrderRequest;
import com.spring.domain.model.response.OrderResponse;
import com.spring.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value="获得用户订单列表")
    @PostMapping(value="/listOrderByUserId")
    public OrderResponse listOrderByUserId(@RequestBody UserOrderRequest userOrderRequest){
        OrderResponse orderResponse=new OrderResponse();
        if(userOrderRequest==null||userOrderRequest.getUserId()<1){
            orderResponse.setCode(StatusCode.Fail_Code);
            orderResponse.setMessage("用户id不正确");
        }
        List<Order> lo=orderService.listOrderByUserId(userOrderRequest.getUserId());
        orderResponse.setData(lo);
        return orderResponse;
    }
}
