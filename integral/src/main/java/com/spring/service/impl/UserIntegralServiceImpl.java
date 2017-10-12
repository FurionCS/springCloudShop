package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.domain.event.UserIntegralEvent;
import com.spring.domain.model.UserIntegral;
import com.spring.persistence.UserIntegralMapper;
import com.spring.service.UserIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 用户积分
 * Created by ErnestCheng on 2017/10/11.
 */
@Service
public class UserIntegralServiceImpl implements UserIntegralService{

    @Autowired
    private UserIntegralMapper userIntegralMapper;

    @Override
    public Integer updateUserIntegral(UserIntegralEvent userIntegralEvent) {
        Preconditions.checkNotNull(userIntegralEvent);
        Preconditions.checkArgument(userIntegralEvent.getUserId()>0);
        //TODO 先判断有没有这个用户积分记录
        UserIntegral userIntegral=userIntegralMapper.getUserIntegral(userIntegralEvent.getUserId());
        if(Objects.isNull(userIntegral)){
              //TODO 添加

        }else{
           // userIntegralMapper.updateUserIntegral(userIntegralEvent.getUserId())
        }
        //有就更新
        return null;
    }
}
