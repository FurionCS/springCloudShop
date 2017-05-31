package com.spring.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.domain.model.Order;
import com.spring.domain.model.User;
import com.spring.domain.response.ObjectDataResponse;
import com.spring.service.OrderService;
import com.spring.web.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value="getUserById",method = RequestMethod.POST)
    public ObjectDataResponse<User> getUserById(Integer userId){
            return userClient.getUserById(userId);
    }

    @RequestMapping(value="listOrder",method = RequestMethod.GET)
    public ObjectDataResponse listOrder(){
        List<Order> lo=orderService.listOrder();
        ObjectDataResponse objectDataResponse=new ObjectDataResponse();
        objectDataResponse.setData(lo);
        return objectDataResponse;
    }
}
