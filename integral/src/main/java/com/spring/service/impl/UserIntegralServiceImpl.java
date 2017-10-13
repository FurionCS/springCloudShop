package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.domain.event.UserIntegralEvent;
import com.spring.domain.model.UserIntegral;
import com.spring.domain.type.IntegralChangeType;
import com.spring.persistence.UserIntegralMapper;
import com.spring.service.UserIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

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
        //TODO 这里有并发问题要解决
        //先判断有没有这个用户积分记录
        UserIntegral userIntegral=userIntegralMapper.getUserIntegral(userIntegralEvent.getUserId());
        if(Objects.isNull(userIntegral)){
             //添加
             // 如果没有用户积分记录，并且这次改变的积分小于0,则不符合逻辑，抛出异常
             Preconditions.checkArgument(userIntegralEvent.getChangeSource()>0);
             userIntegral=new UserIntegral(UUID.randomUUID().toString(),userIntegral.getUserId(), Timestamp.valueOf(LocalDateTime.now()),Timestamp.valueOf(LocalDateTime.now()),
                     userIntegralEvent.getChangeSource().longValue(),userIntegralEvent.getChangeSource().longValue(),0l);
            return userIntegralMapper.addUserIntegral(userIntegral);
        }else{  //有就更新
            //获得历史积分，只有加积分时，或者红字情况下会改变历史积分
            long hisSource=userIntegral.getHisSource();
            if(Objects.equals(userIntegralEvent.getChangeTypeStatus(), IntegralChangeType.add)||Objects.equals(userIntegralEvent.getChangeTypeStatus(), IntegralChangeType.red)){
                hisSource+=userIntegralEvent.getChangeSource().longValue();
            }
            //获得现有积分，加积分，和消费积分，红字情况下
            long nowSource=userIntegral.getNowSource()+userIntegralEvent.getChangeSource().longValue();
            //获得使用过的积分，消费积分，积分购买不能退积分
            long usedSource=userIntegral.getUsedSource();
            if(Objects.equals(userIntegralEvent.getChangeTypeStatus(), IntegralChangeType.used)){
                //消费积分应该是小于0的
                Preconditions.checkArgument(userIntegralEvent.getChangeSource()<0);
                usedSource+=userIntegralEvent.getChangeSource();
            }
            return userIntegralMapper.updateUserIntegral(userIntegralEvent.getUserId(),nowSource,hisSource,usedSource);
        }
    }
}
