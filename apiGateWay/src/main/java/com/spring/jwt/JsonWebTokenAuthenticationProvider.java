
package com.spring.jwt;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * 认证是由 AuthenticationManager 来管理的，但是真正进行认证的是 AuthenticationManager 中定义的 AuthenticationProvider。
 * 头部信息将被转换为Spring Authentication对象，名称为PreAuthenticatedAuthenticationToken 我们需要一个授权提供者读取这个记号
 * ，然后验证它，然后转换为我们自己的定制授权对象，就是把header里的token转化成我们自己的授权对象。
 * 然后把解析之后的对象返回给Spring Security，这里就相当于完成了token->session的转换。
 * <p/>
 */
@Component
public class JsonWebTokenAuthenticationProvider implements AuthenticationProvider {

    private JsonWebTokenUtility tokenService = new JsonWebTokenUtility();

    /**
     * 在authenticate方法中，首先可以根据用户名获取到用户信息，再者可以拿自定义参数和用户信息做逻辑验证，如密码的验证
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        Authentication authenticatedUser = null;
        // Only process the PreAuthenticatedAuthenticationToken
        if (authentication.getClass().
                isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
                && authentication.getPrincipal() != null) {
            String tokenHeader = (String) authentication.getPrincipal();
            UserDetails userDetails = parseToken(tokenHeader);
            if (userDetails != null) {
                authenticatedUser =
                        new JsonWebTokenAuthentication(userDetails, tokenHeader);
            }
        } else {
            // It is already a JsonWebTokenAuthentication
            authenticatedUser = authentication;
        }
        return authenticatedUser;
    }

    private UserDetails parseToken(String tokenHeader) {

        UserDetails principal = null;
        AuthTokenDetails authTokenDetails =
                tokenService.parseAndValidate(tokenHeader);

        if (authTokenDetails != null) {
            List<GrantedAuthority> authorities =
                    authTokenDetails.getRoleNames().stream()
                            .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            // userId介入Spring Security
            principal = new User(authTokenDetails.getId().toString(), "",
                    authorities);
        }

        return principal;
    }

    /**
     * 这个类中先执行这个方法，如果为true,执行authenticate方法
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return
                authentication.isAssignableFrom(
                        PreAuthenticatedAuthenticationToken.class)||
                        authentication.isAssignableFrom(
                                JsonWebTokenAuthentication.class);
    }

}
