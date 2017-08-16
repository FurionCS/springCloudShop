package com.spring.config;

import com.spring.jwt.JsonWebTokenSecurityConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;


/**
 * @Description 权限配置
 * @Author ErnestCheng
 * @Date 2017/6/22.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends JsonWebTokenSecurityConfig {

    @Value("${auth.allow.url:''}")
    private String[] allowUrls;

    @Override
    protected void setupAuthorization(HttpSecurity http) throws Exception {
        for(String url:allowUrls){
            addMatchers(http.authorizeRequests(),url);
        }
        http.authorizeRequests().anyRequest().authenticated();
    }

    private ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry  addMatchers(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry auth,String url){
         return    auth.antMatchers(url).permitAll();
    }



}
