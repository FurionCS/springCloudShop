package com.spring.config;

import com.spring.web.TccErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zhao Junjian
 */
@Configuration
public class BeanConfiguration {
    @Bean
    public TccErrorDecoder tccErrorDecoder() {
        return new TccErrorDecoder();
    }
}
