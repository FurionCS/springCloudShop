package com.spring.listener;

import com.google.common.eventbus.Subscribe;
import com.spring.domain.event.UserIntegralEvent;
import com.spring.publisher.UserIntegralPublisher;
import com.spring.service.UserIntegralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 用户积分监听器
 * Created by ErnestCheng on 2017/10/12.
 */
@Service
public class UserIntegralListener{
    private static final Logger LOGGER= LoggerFactory.getLogger(UserIntegralListener.class);

    @Autowired
    private UserIntegralService userIntegralService;



    /**
     * 监听器
     * @param userIntegralPublisher
     */
    @Autowired
    public UserIntegralListener(UserIntegralPublisher userIntegralPublisher){
        userIntegralPublisher.register(this);
    }

    /**
     * 处理方法
     */
    @Subscribe
    public void regHandler(UserIntegralEvent userIntegralEvent) throws InterruptedException {
        //TODO  判断对象是否合法，不合法放入mongo中记录
        //TODO 合法就更改用户积分
        userIntegralService.updateUserIntegral(userIntegralEvent);
    }
}
