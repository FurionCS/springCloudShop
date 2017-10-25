package com.spring.config;


import com.spring.domain.config.RabbitBeanConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabbit队列配置
 */
@Configuration
public class RabbitmqQueueConfig {


    @Bean(name=RabbitBeanConfig.USER_LOGIN_Q)
    public Queue userLoginQueue(){
        return new Queue(RabbitBeanConfig.USER_LOGIN_Q);
    }

    @Bean
    public FanoutExchange exchangeT(){
        return new FanoutExchange(RabbitBeanConfig.USER_LOGIN_E);
    }

    @Bean
    Binding bindingExchangeUserLoginMessage(@Qualifier(RabbitBeanConfig.USER_LOGIN_Q) Queue userLoginQ, FanoutExchange exchange){
        return BindingBuilder.bind(userLoginQ).to(exchange);
    }
}
