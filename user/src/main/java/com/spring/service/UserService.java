package com.spring.service;

import com.spring.domain.model.User;

/**
 * @Description 用户service接口
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
public interface UserService {
    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);
}
