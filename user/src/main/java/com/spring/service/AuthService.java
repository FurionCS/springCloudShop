package com.spring.service;

import com.spring.domain.model.UserAuth;
import com.spring.domain.model.response.ObjectDataResponse;

/**
 * @Description 权限
 * @Author ErnestCheng
 * @Date 2017/6/23.
 */
public interface AuthService {
    /**
     * 登入
     * @param username
     * @param password
     * @return
     */
    String login(String username,String password);

    /**
     * 注册
     * @param userAuth
     * @return
     */
    UserAuth register(UserAuth userAuth);

    /**
     * 刷新
     * @param oldToken
     * @return
     */
    String refresh(String oldToken);
}
