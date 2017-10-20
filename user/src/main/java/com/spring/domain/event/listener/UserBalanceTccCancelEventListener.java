package com.spring.domain.event.listener;

import com.google.common.base.Preconditions;
import com.spring.domain.model.UserBalanceTcc;
import com.spring.domain.event.UserBalanceTccCancelEvent;
import com.spring.service.UserBalanceTccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:用户余额事件监听
 * @Author : Mr.Cheng
 * @Date:2017/6/4
 */
@Component
public class UserBalanceTccCancelEventListener implements ApplicationListener<UserBalanceTccCancelEvent> {

    private final UserBalanceTccService userBalanceTccService;
    @Autowired
    public UserBalanceTccCancelEventListener(UserBalanceTccService userBalanceTccService){
        this.userBalanceTccService=userBalanceTccService;
    }
    @Async
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void onApplicationEvent(UserBalanceTccCancelEvent userBalanceTccCancelEvent) {
        Preconditions.checkNotNull(userBalanceTccCancelEvent);
        final UserBalanceTcc userBalanceTcc=(UserBalanceTcc)userBalanceTccCancelEvent.getSource();
        Preconditions.checkNotNull(userBalanceTcc);
        userBalanceTccService.cancelReservation(userBalanceTcc);
    }
}
