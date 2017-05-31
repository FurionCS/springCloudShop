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

    /**
     * 通过用户id获得用户信息
     * @param userId
     * @return
     */
    User getUserById(Integer userId);
}
