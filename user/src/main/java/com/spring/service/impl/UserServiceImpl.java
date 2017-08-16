package com.spring.service.impl;


import com.google.common.base.Preconditions;
import com.spring.common.model.exception.GlobalException;
import com.spring.common.model.model.RedisKey;
import com.spring.common.model.util.tools.SecurityUtil;
import com.spring.domain.model.Role;
import com.spring.domain.model.User;
import com.spring.domain.model.UserRole;
import com.spring.domain.model.VO.UserRoleVO;
import com.spring.domain.model.request.UserUpdateRequest;
import com.spring.persistence.RoleMapper;
import com.spring.persistence.UserMapper;
import com.spring.persistence.UserRoleMapper;
import com.spring.repository.UserAuthRepository;
import com.spring.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @Description 用户接口实现
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获得加密过的密码
     * @param salt
     * @param password
     * @param type
     * @return
     * @throws NoSuchAlgorithmException
     */
    private String getSecurityPassword(String salt,String password,Integer type) throws NoSuchAlgorithmException {
        return SecurityUtil.md5(salt, password, type);
    }

    @Override
    public void addUser(User user) {
        try {
            String password =this.getSecurityPassword(user.getUserName(),user.getPassword(),32);
            user.setPassword(password);
            userMapper.addUser(user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public User getUserById(Integer userId) {
        User user = (User) redisTemplate.opsForValue().get(RedisKey.user + userId);
        if (user == null) {
            user = userMapper.getUserById(userId);
            redisTemplate.opsForValue().set(RedisKey.user + userId, user);
        }
        return user;
    }

    @Override
    //   @Cacheable(value="shop_user_role",key="T(String).valueOf(#userId)")
    public UserRoleVO listUserRoleVO(Integer userId) {
        UserRoleVO userRoleVO = userRoleMapper.getUserRoleVO(userId);
        logger.info(userRoleVO);
        return userRoleVO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUserAndRole(User user, List<Integer> roleIds) {
        //添加用户
        this.addUser(user);
        //添加用户角色
        if (roleIds != null && roleIds.size() > 0) {
            List<UserRole> userRoles = new ArrayList<>();
            roleIds.forEach(roleId -> {
                Role role = roleMapper.getRole(roleId);
                if (role != null) {
                    UserRole userRole = new UserRole();
                    userRole.setRoleId(roleId);
                    userRole.setUserId(user.getId());
                    userRole.setCreateTime(OffsetDateTime.now());
                    userRole.setStatus(1);
                    userRoles.add(userRole);
                } else {
                    throw new GlobalException("找不到id为" + roleId + "的角色");
                }
            });
            userRoleMapper.addUserRoleList(userRoles);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateUser(UserUpdateRequest userUpdateRequest) {
        int flag = userMapper.updateUser(userUpdateRequest.getId(), userUpdateRequest.getUserName(), userUpdateRequest.getIdCard(),null);
        if (flag == 1) {
            User user = (User) redisTemplate.opsForValue().get(RedisKey.user + userUpdateRequest.getId());
            if (user != null) {
                user.setIdCard(userUpdateRequest.getIdCard());
                user.setUserName(userUpdateRequest.getUserName());
                redisTemplate.opsForValue().set(RedisKey.user + user.getId(), user);
            }
        }
        return flag;
    }

    @Override
    public int deleteUserByUserId(Integer userId) {
        int flag = userMapper.deleteUserByUserId(userId);
        if (flag == 1) {
            redisTemplate.delete(RedisKey.user+userId);
        }
        return flag;
    }

    @Override
    public int updatePassword(String newPassword, String oldPassword, User user){
        Preconditions.checkNotNull(user);
        try {
            String oldSecurityPassword=this.getSecurityPassword(user.getUserName(),oldPassword,32);
            if(Objects.equals(oldSecurityPassword,user.getPassword())){
                String newSecurityPassowrd=this.getSecurityPassword(user.getUserName(),newPassword,32);
                user.setPassword(newSecurityPassowrd);
                int flag = userMapper.updateUser(user.getId(), user.getUserName(), null,newSecurityPassowrd);
                if(flag==1) {
                    redisTemplate.opsForValue().set(RedisKey.user + user.getId(), user);
                }
                return flag;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
