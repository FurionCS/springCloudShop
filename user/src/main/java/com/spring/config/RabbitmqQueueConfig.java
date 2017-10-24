package com.spring.config;


import com.spring.domain.dto.RabbitBean;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * rabbit队列配置
 */
@Configuration
public class RabbitmqQueueConfig {

    @Bean(name=RabbitBean.userLoginT)
    public Queue userLoginQueueT(){
        return new Queue(RabbitBean.userLoginT);
    }

    @Bean
    public TopicExchange exchangeT(){
        return new TopicExchange(RabbitBean.userLoginE);
    }

    @Bean
    Binding bindingExchangeUserLoginMessage(@Qualifier(RabbitBean.userLoginT) Queue integralQuenu, TopicExchange exchange){
        return BindingBuilder.bind(integralQuenu).to(exchange).with(RabbitBean.userLoginR);
    }
}
