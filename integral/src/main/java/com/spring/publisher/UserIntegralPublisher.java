package com.spring.publisher;

import com.spring.domain.eventBus.GuavaDomainEventPublisher;
import org.springframework.stereotype.Service;

/**
 * 用户积分发布
 * Created by ErnestCheng on 2017/10/12.
 */
@Service
public class UserIntegralPublisher extends GuavaDomainEventPublisher{
    @Override
    public String identify() {
        return "user_integral_publisher";
    }
}
