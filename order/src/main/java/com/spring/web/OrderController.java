package com.spring.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.domain.model.Order;
import com.spring.domain.model.User;
import com.spring.domain.request.PlaceOrderRequest;
import com.spring.domain.response.ObjectDataResponse;
import com.spring.service.OrderService;
import com.spring.web.client.UserClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description 订单控制器
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserClient userClient;

    @ApiOperation(value="获得用户信息通过id")
    @RequestMapping(value="getUserById",method = RequestMethod.POST)
    public ObjectDataResponse<User> getUserById(Integer userId){
            return userClient.getUserById(userId);
    }

    @ApiOperation(value="获得所有订单列表")
    @RequestMapping(value="listOrder",method = RequestMethod.GET)
    public ObjectDataResponse listOrder(){
        List<Order> lo=orderService.listOrder();
        ObjectDataResponse objectDataResponse=new ObjectDataResponse();
        objectDataResponse.setData(lo);
        return objectDataResponse;
    }

    /**
     * 预留资源
     * @return
     */
    @ApiOperation(value="下预订单")
    @RequestMapping(value="plcaseOrder",method = RequestMethod.POST)
    public ObjectDataResponse<Order> placeOrder(@Valid @RequestBody PlaceOrderRequest placeOrderRequest, BindingResult result){
       ObjectDataResponse<Order> objectDataResponse= orderService.placeOrder(placeOrderRequest);
        return objectDataResponse;
    }

}
