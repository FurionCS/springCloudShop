package com.spring.service.impl;

import com.spring.domain.model.JwtUser;
import com.spring.domain.model.UserAuth;
import com.spring.domain.model.response.ObjectDataResponse;
import com.spring.repository.UserAuthRepository;
import com.spring.service.AuthService;
import com.spring.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

/**
 * @Description 权限
 * @Author ErnestCheng
 * @Date 2017/6/23.
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public UserAuth register(UserAuth userAuth) {
        final String username = userAuth.getUserName();
        if(userAuthRepository.findByUserName(username)!=null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userAuth.getPassword();
        userAuth.setPassword(encoder.encode(rawPassword));
        userAuth.setLastPasswordResetDate(new Date());
        userAuth.setRoles(userAuth.getRoles());
        return userAuthRepository.insert(userAuth);
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
