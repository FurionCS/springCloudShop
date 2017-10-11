package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.domain.model.IntegralChange;
import com.spring.domain.type.IntegralChangeStatus;
import com.spring.persistence.IntegralChangeMapper;
import com.spring.service.IntegralChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
@Service
public class IntegralChangeServiceImpl implements IntegralChangeService {

    @Autowired
    private IntegralChangeMapper integralChangeMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Integer addIntegralChange(IntegralChange integralChange) {
        Preconditions.checkNotNull(integralChange);
        //  新增积分规则
        integralChange.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        integralChange.setUpdateTime(integralChange.getCreateTime());
        //TODO  更新redis列表
        return   integralChangeMapper.addIntegralChange(integralChange);
    }

    @Override
    public List<IntegralChange> listIntegralChange(IntegralChangeStatus integralChangeStatus) {
        return null;
    }

    @Override
    public Integer updateIntegral(IntegralChange integralChange) {
        return null;
    }
}
