package com.spring.factory;

import com.spring.domain.model.JwtUser;
import com.spring.domain.model.UserAuth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description jwtUser工厂
 * @Author ErnestCheng
 * @Date 2017/6/22.
 */
public final class JwtUserFactory {
    private JwtUserFactory() {
    }

    public static JwtUser create(UserAuth user) {
        return new JwtUser(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles()),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
