
package com.spring.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 授权过滤器，读取头部信息
 * 继承RequestHeaderAuthenticationFilter  spring security 过滤器 获得请求头设置到
 * spring security 的登录验证是由org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter这个过滤器来完成的
 * 在该类的父类AbstractAuthenticationProcessingFilter中有一个AuthenticationManager接口属性
 * 验证工作主要是通过这个AuthenticationManager接口的实例来完成的
 * <p/>
 * JTW验证
 */
@Component
public class JsonWebTokenAuthenticationFilter extends RequestHeaderAuthenticationFilter {

    public JsonWebTokenAuthenticationFilter() {
        // Don't throw exceptions if the header is missing
        this.setExceptionIfHeaderMissing(false);

        // This is the request header it will look for
        this.setPrincipalRequestHeader("Authorization");
    }

    @Override
    @Autowired
    public void setAuthenticationManager(
            AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}