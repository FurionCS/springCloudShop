package com.spring.config;


import com.spring.domain.dto.RabbitBean;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * rabbit队列配置
 */
@Configuration
public class RabbitmqQueueConfig {

    @Bean(name=RabbitBean.userLoginQ)
    public Queue userLoginQueueD(){
        return new Queue(RabbitBean.userLoginQ);
    }

    @Bean
    public DirectExchange exchangeD(){
        return new DirectExchange(RabbitBean.userLoginE);
    }

    @Bean
    Binding bindingExchangeUserLoginMessage(@Qualifier(RabbitBean.userLoginQ) Queue integralQuenu, DirectExchange exchange){
        return BindingBuilder.bind(integralQuenu).to(exchange).with(RabbitBean.userLoginR);
    }
}
