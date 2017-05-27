package com.spring.service.impl;


import com.spring.domain.model.User;
import com.spring.persistence.UserMapper;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 用户接口实现
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }
}
