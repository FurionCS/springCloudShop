package com.spring.event.publisher;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.spring.domain.dto.RabbitBean;
import com.spring.event.UserLoginEvent;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 用户登录消息发送者
 */
@Component
public class UserLoginPublisher {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    /**
     * 发送用户积分事件
     */
    public void sendUserLoginEvent(String code,Integer userId,String remark){
        UserLoginEvent userLoginEvent =new UserLoginEvent(userId, Timestamp.valueOf(LocalDateTime.now()),remark,code,new BigDecimal(1));
        rabbitmqTemplate.convertAndSend(RabbitBean.userLoginE,RabbitBean.userLoginR, JSON.toJSONString(userLoginEvent));
    }

}
