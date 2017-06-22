package com.spring.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Description jwtUser
 * @Author ErnestCheng
 * @Date 2017/6/22.
 */
public class JwtUser implements UserDetails{

    private final String id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(
            String id,
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities
            ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @JsonIgnore
    public String getId(){
        return id;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
