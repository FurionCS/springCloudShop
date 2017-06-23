package com.spring.service;

import com.spring.domain.model.User;
import com.spring.domain.model.UserAuth;
import com.spring.domain.model.VO.UserRoleVO;

import java.util.List;

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

    /**
     * 获得所有用户
     * @return
     */
    List<UserAuth> listUser();

    /**
     * 获得用户角色信息
     * @param userId
     * @return
     */
    UserRoleVO listUserRoleVO(Integer userId);
}
