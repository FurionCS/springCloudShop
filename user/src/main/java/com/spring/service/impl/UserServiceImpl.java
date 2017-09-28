package com.spring.service.impl;


import com.google.common.base.Preconditions;
import com.spring.common.model.StatusCode;
import com.spring.common.model.exception.GlobalException;
import com.spring.common.model.model.ErrorInfo;
import com.spring.common.model.model.RedisKey;
import com.spring.common.model.util.tools.BeanToMapUtil;
import com.spring.common.model.util.tools.SecurityUtil;
import com.spring.domain.model.Role;
import com.spring.domain.model.User;
import com.spring.domain.model.UserRole;
import com.spring.domain.request.UserUpdateRequest;
import com.spring.domain.type.UserStatus;
import com.spring.domain.vo.UserRoleVO;
import com.spring.persistence.RoleMapper;
import com.spring.persistence.UserMapper;
import com.spring.persistence.UserRoleMapper;
import com.spring.repository.ErrorRepository;
import com.spring.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * @Description 用户接口实现
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ErrorRepository errorRepository;

    /**
     * 获得加密过的密码
     *
     * @param salt
     * @param password
     * @param type
     * @return
     * @throws NoSuchAlgorithmException
     */
    private String getSecurityPassword(String salt, String password, Integer type) throws NoSuchAlgorithmException {
        return SecurityUtil.md5(salt, password, type);
    }

    @Override
    public void addUser(User user) {
        try {
            String password = this.getSecurityPassword(user.getUserName(), user.getPassword(), 32);
            user.setPassword(password);
            userMapper.addUser(user);
        } catch (NoSuchAlgorithmException e) {
            ErrorInfo errorInfo = new ErrorInfo(StatusCode.Update_Fail, "加密失败", user);
            errorRepository.insert(errorInfo);
            LOGGER.error("加密异常");
        }
    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public User getUserById(Integer userId) throws IllegalAccessException, IntrospectionException, InvocationTargetException, InstantiationException {
        Map userM = redisTemplate.opsForHash().entries(RedisKey.userh+userId);
        if (Objects.isNull(userM) || userM.isEmpty()) {
            User user = userMapper.getUserById(userId);
            redisTemplate.opsForHash().putAll(RedisKey.userh+userId,BeanToMapUtil.convertBean(user));
            return user;
        }
        return (User)BeanToMapUtil.convertMap(User.class,userM);
    }

    @Override
    public UserRoleVO listUserRoleVO(Integer userId) {
        UserRoleVO userRoleVO = userRoleMapper.getUserRoleVO(userId);
        LOGGER.info(userRoleVO);
        return userRoleVO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUserAndRole(User user, List<Integer> roleIds) {
        //添加用户
        this.addUser(user);
        //添加用户角色
        if (roleIds != null && !roleIds.isEmpty()) {
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
        int flag = userMapper.updateUser(userUpdateRequest.getId(), null, userUpdateRequest.getIdCard(), null);
        if (flag == 1) {
            String idCardC = (String) redisTemplate.opsForHash().get(RedisKey.userh+userUpdateRequest.getId(),"idCard");
            if(idCardC!=null) {
                redisTemplate.opsForHash().put(RedisKey.userh + userUpdateRequest.getId(), "idCard", userUpdateRequest.getIdCard());
            }
        }
        return flag;
    }

    @Override
    public int deleteUserByUserId(Integer userId) {
        int flag = userMapper.deleteUserByUserId(userId);
        if (flag == 1) {
            redisTemplate.delete(RedisKey.userh+userId);
        }
        return flag;
    }

    @Override
    public int updatePassword(String newPassword, String oldPassword, User user) {
        Preconditions.checkNotNull(user);
        try {
            String oldSecurityPassword = this.getSecurityPassword(user.getUserName(), oldPassword, 32);
            if (Objects.equals(oldSecurityPassword, user.getPassword())) {
                String newSecurityPassword = this.getSecurityPassword(user.getUserName(), newPassword, 32);
                user.setPassword(newSecurityPassword);
                int flag = userMapper.updateUser(user.getId(), user.getUserName(), null, newSecurityPassword);
                if (flag == 1) {
                    redisTemplate.opsForHash().put(RedisKey.userh+user.getId(),"password",newSecurityPassword);
                }
                return flag;
            }
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<User> findUser(UserStatus status, String startDate, String endDate, int pageIndex, int pageSize) {
        final int startIndex = (pageIndex - 1) * pageSize + 1;
        final int endIndex = startIndex + pageSize;
        //TODO 
        List<User> userList = userMapper.findUser(status, startIndex, endIndex, startDate, endDate);
        return userList;
    }
}
