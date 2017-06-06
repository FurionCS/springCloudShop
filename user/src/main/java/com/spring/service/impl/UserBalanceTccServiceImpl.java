package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.common.model.StatusCode;
import com.spring.common.model.exception.GlobalException;
import com.spring.common.model.model.ErrorInfo;
import com.spring.common.model.model.RedisKey;
import com.spring.domain.model.User;
import com.spring.domain.model.UserBalanceTcc;
import com.spring.domain.model.type.TccStatus;
import com.spring.event.UserBalanceTccCancelEvent;
import com.spring.persistence.UserBalanceTccMapper;
import com.spring.persistence.UserMapper;
import com.spring.repository.ErrorRepository;
import com.spring.service.UserBalanceTccService;
import com.spring.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;

/**
 * @Description 用户余额tcc
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Service
public class UserBalanceTccServiceImpl implements UserBalanceTccService,ApplicationContextAware{
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserBalanceTccMapper userBalanceTccMapper;


    private ApplicationContext context;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ErrorRepository errorRepository;

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
        //保存到redis
        user.setBalance(user.getBalance()-amount);
        redisTemplate.opsForValue().set(RedisKey.user+":"+userId,user);

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

    /**
     * 自动检查过期资源，通过定时轮询数据库，这个做法有问题，会出现资源竞争和重复检查问题
     */
    @Scheduled(fixedDelay = 100)
    public void autoExpireReservation(){
        final Set<UserBalanceTcc>  userBalanceTccSet=userBalanceTccMapper.selectExpireReservation(100);
        userBalanceTccSet.forEach(userBalanceTcc -> {
            context.publishEvent(new UserBalanceTccCancelEvent(userBalanceTcc));
        });
    }

    @Override
    public void cancelReservation(Integer id){
        Preconditions.checkNotNull(id);
        UserBalanceTcc userBalanceTcc=userBalanceTccMapper.getUserBalanceTcc(id);
        this.cancelReservation(userBalanceTcc);
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void cancelReservation(UserBalanceTcc userBalanceTcc) {
        Preconditions.checkNotNull(userBalanceTcc);
        Preconditions.checkNotNull(userBalanceTcc.getId());
        Preconditions.checkNotNull(userBalanceTcc.getStatus());
        if(userBalanceTcc.getStatus()==TccStatus.TRY){
            //删除用户资源信息
            int flag=userBalanceTccMapper.deleteReservation(userBalanceTcc.getId());
            if(flag>0){
                int flag2=userMapper.updateBalance(userBalanceTcc.getUserId(),userBalanceTcc.getAmount());
                // 更新用户余额
                if(flag2==0){
                    ErrorInfo errorInfo=new ErrorInfo<>(StatusCode.Update_Fail,"余额更新失败",null,userBalanceTcc,OffsetDateTime.now());
                    errorRepository.insert(errorInfo);
                    throw new GlobalException("余额更新失败");
                }
                //更新redis
                User user=(User)redisTemplate.opsForValue().get(RedisKey.user+":"+userBalanceTcc.getUserId());
                if(user!=null){
                    user.setBalance(user.getBalance()+userBalanceTcc.getAmount());
                    redisTemplate.opsForValue().set(RedisKey.user+":"+userBalanceTcc.getUserId(),user);
                }
            }
        }
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }
}
