package com.spring.event;

import com.spring.domain.model.UserBalanceTcc;
import org.springframework.context.ApplicationEvent;

/**
 * @Description:用户余额取消事件
 * @Author : Mr.Cheng
 * @Date:2017/6/4
 */

public class UserBalanceTccCancelEvent extends ApplicationEvent {
    private static final long serialVersionUID = 8217090130282205938L;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserBalanceTccCancelEvent(UserBalanceTcc source) {
        super(source);
    }
}
