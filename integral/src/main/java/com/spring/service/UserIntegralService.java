package com.spring.service;

import com.spring.domain.event.UserIntegralEvent;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
public interface UserIntegralService {
    /**
     * 通过事件更新用户积分
     * @param userIntegralEvent
     * @return
     */
    Integer updateUserIntegral(UserIntegralEvent userIntegralEvent);
}
