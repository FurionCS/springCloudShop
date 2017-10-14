package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.domain.event.UserIntegralEvent;
import com.spring.domain.model.IntegralChange;
import com.spring.domain.model.UserIntegralDetail;
import com.spring.persistence.IntegralChangeMapper;
import com.spring.persistence.UserIntegralDetailMapper;
import com.spring.persistence.UserIntegralMapper;
import com.spring.publisher.UserIntegralPublisher;
import com.spring.service.UserIntegralDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
@Service
public class UserIntegralDetailServiceImpl implements UserIntegralDetailService {

    @Autowired
    private UserIntegralDetailMapper userIntegralDetailMapper;

    @Autowired
    private IntegralChangeMapper integralChangeMapper;

    @Autowired
    private UserIntegralPublisher userIntegralPublisher;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer addUserIntegralDetail(UserIntegralDetail userIntegralDetail) {
        Preconditions.checkNotNull(userIntegralDetail);
        //添加用户积分
        int flag=userIntegralDetailMapper.addUserIntegralDetail(userIntegralDetail);
        if(flag>0) {
            //组装数据
            IntegralChange integralChange = integralChangeMapper.getIntegralChange(userIntegralDetail.getChangeId());
            UserIntegralEvent userIntegralEvent = new UserIntegralEvent(userIntegralDetail.getUserId(), userIntegralDetail.getChangeSource(), userIntegralDetail.getRemark(), integralChange.getChangeType());
            userIntegralPublisher.asyncPublish(userIntegralEvent);
        }
        return flag;
    }
}
