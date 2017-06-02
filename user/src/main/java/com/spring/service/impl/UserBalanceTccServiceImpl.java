package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.common.model.StatusCode;
import com.spring.common.model.exception.GlobalException;
import com.spring.domain.model.User;
import com.spring.domain.model.UserBalanceTcc;
import com.spring.domain.model.type.TccStatus;
import com.spring.persistence.UserBalanceTccMapper;
import com.spring.persistence.UserMapper;
import com.spring.service.UserBalanceTccService;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @Description 用户余额tcc
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Service
public class UserBalanceTccServiceImpl implements UserBalanceTccService{
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserBalanceTccMapper userBalanceTccMapper;

    private Long expireSeconds=15L;
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public UserBalanceTcc trying(Integer userId, Double amount) {
        Preconditions.checkArgument(userId>0);
        Preconditions.checkArgument(amount>0);
        final User user=userService.getUserById(userId);
        if (user == null) {
            throw new GlobalException("用户不存在", StatusCode.Data_Not_Exist);
        }
        //消费余额
        final int isLock=userMapper.consumeBalance(userId,amount);
        if(isLock==0){
            throw new GlobalException("消费余额失败",StatusCode.Action_Fail);
        }
        UserBalanceTcc tcc=new UserBalanceTcc();
        tcc.setAmount(amount);
        tcc.setStatus(TccStatus.TRY);
        tcc.setUserId(userId);
        tcc.setExpireTime(OffsetDateTime.now().plusSeconds(expireSeconds));
        tcc.setCreateTime(OffsetDateTime.now());
        OffsetDateTime defaultDateTime=OffsetDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(8));
        tcc.setUpdateTime(defaultDateTime);
        tcc.setDeleteTime(defaultDateTime);
        userBalanceTccMapper.addUserBalanceTcc(tcc);
        return tcc;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void confirm(Integer reservationId) {
        Preconditions.checkNotNull(reservationId);
        UserBalanceTcc userBalanceTcc=userBalanceTccMapper.getUserBalanceTcc(reservationId);
        if(userBalanceTcc==null){
            throw new GlobalException("预留资源不存在",StatusCode.Data_Not_Exist);
        }
        if(userBalanceTcc.getStatus()==TccStatus.TRY){
            int flag=userBalanceTccMapper.updateUserBalanceTccStatus(reservationId);
            if(flag==0){
                throw new GlobalException("resource " + reservationId + " has been cancelled");
            }
        }
    }
}
