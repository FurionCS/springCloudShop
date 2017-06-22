package com.spring.service.impl;


import com.spring.domain.model.User;
import com.spring.domain.model.UserAuth;
import com.spring.persistence.UserMapper;
import com.spring.repository.UserAuthRepository;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户接口实现
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAuthRepository userAuthRepository;
    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    @Cacheable(value="shop_user_id",key = "T(String).valueOf(#userId)")
    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public List<UserAuth> listUser() {
        return userAuthRepository.findAll();
    }
}
