package com.spring.config;


import com.spring.domain.config.RabbitBeanConfig;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * rabbit队列配置
 */
@Configuration
public class RabbitmqQueueConfig {
    @Bean
    public FanoutExchange exchangeT(){
        return new FanoutExchange(RabbitBeanConfig.USER_LOGIN_E);
    }
}
