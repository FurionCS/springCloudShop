package com.spring.service.impl;


import com.spring.common.model.util.tools.SecurityUtil;
import com.spring.domain.model.User;
import com.spring.domain.model.UserAuth;
import com.spring.domain.model.UserRole;
import com.spring.domain.model.VO.UserRoleVO;
import com.spring.domain.model.request.UserRoleRequest;
import com.spring.persistence.UserMapper;
import com.spring.persistence.UserRoleMapper;
import com.spring.repository.UserAuthRepository;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
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

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public void addUser(User user) {
        try {
            String password=SecurityUtil.md5(user.getUserName(),user.getPassword(),32);
            user.setPassword(password);
            userMapper.addUser(user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
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

    @Override
    @Cacheable(value="shop_user_role",key="T(String).valueOf(#userId)")
    public UserRoleVO listUserRoleVO(Integer userId) {
        return userRoleMapper.getUserRoleVO(userId);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void addUserAndRole(User user,List<Integer> roleIds) {
        //添加用户
        this.addUser(user);
        //添加用户角色
        if(roleIds!=null&&roleIds.size()>0) {
            List<UserRole> userRoles = new ArrayList<>();
            roleIds.forEach(roleId -> {
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(user.getId());
                userRole.setCreateTime(OffsetDateTime.now());
                userRole.setStatus(1);
                userRoles.add(userRole);
            });
            userRoleMapper.addUserRoleList(userRoles);
        }
    }
}
