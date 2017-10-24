package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.common.model.model.RedisKey;
import com.spring.domain.model.User;
import com.spring.domain.model.UserAddr;
import com.spring.domain.type.UserAddrStatus;
import com.spring.persistence.UserAddrMapper;
import com.spring.service.UserAddrService;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author cs
 * @date 2017/09/30
 */
@Service
public class UserAddrServiceImpl implements UserAddrService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddrMapper userAddrMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public boolean addUserAddr(UserAddr userAddr) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException {
        Preconditions.checkNotNull(userAddr,"用户地址不能为空");
        Preconditions.checkNotNull(userAddr.getUserId(),"用户id不能为空");
        //查询是否有该用户
        User user=userService.getUserById(userAddr.getUserId());
        if(Objects.isNull(user)){
            return false;
        }
        //添加用户地址
        userAddr.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        userAddr.setUpdateTime(userAddr.getCreateTime());
        userAddr.setStatus(UserAddrStatus.正常);
        Integer count=userAddrMapper.addUserAddr(userAddr);
        if(count>0){
            //更新缓存
            redisTemplate.opsForSet().add(RedisKey.userAddr+userAddr.getUserId(),userAddr);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<UserAddr> findUserAddr() {
        return null;
    }
}
