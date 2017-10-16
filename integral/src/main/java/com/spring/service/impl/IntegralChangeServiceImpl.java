package com.spring.service.impl;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.spring.common.model.model.RedisKey;
import com.spring.domain.model.IntegralChange;
import com.spring.domain.type.IntegralChangeStatus;
import com.spring.persistence.IntegralChangeMapper;
import com.spring.service.IntegralChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
        Integer flag = integralChangeMapper.addIntegralChange(integralChange);
        if (flag == 1) {
            //插入到redis积分列表中
            redisTemplate.opsForList().leftPush(RedisKey.integralChange + integralChange.getStatus().getStatus(), integralChange);
        }
        return flag;
    }

    @Override
    public List<IntegralChange> listIntegralChange(IntegralChangeStatus integralChangeStatus) {
        Preconditions.checkNotNull(integralChangeStatus);
        //从redis中获得
        List<IntegralChange> integralChangeList = redisTemplate.opsForList().range(RedisKey.integralChange + integralChangeStatus.getStatus(), 0, -1);
        //有直接返回
        if (Objects.nonNull(integralChangeList) && !integralChangeList.isEmpty()) {
            return integralChangeList;
        } else {
            //没有查数据库
            integralChangeList = integralChangeMapper.listIntegralChange(integralChangeStatus);
            if (Objects.nonNull(integralChangeList) && !integralChangeList.isEmpty()) {
                redisTemplate.opsForList().leftPushAll(RedisKey.integralChange + integralChangeStatus.getStatus(), integralChangeList);
            }
            return integralChangeList;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public Integer updateIntegral(IntegralChange integralChange) {
        Preconditions.checkNotNull(integralChange);
        //从数据库中获得旧的
        IntegralChange integralChange1Old = integralChangeMapper.getIntegralChange(integralChange.getId());
        if (Objects.nonNull(integralChange)) {
            integralChangeMapper.updateIntegralChange(integralChange);
            redisTemplate.opsForList().remove(RedisKey.integralChange + integralChange1Old.getStatus().getStatus(), 1, integralChange1Old);
            int status = Objects.isNull(integralChange.getStatus()) ? integralChange1Old.getStatus().getStatus() : integralChange.getStatus().getStatus();
            redisTemplate.opsForList().leftPush(RedisKey.integralChange + status, integralChangeMapper.getIntegralChange(integralChange.getId()));
            return 1;
        }
        return 0;
    }

    @Override
    public IntegralChange getIntegralChange(Integer id) {
        Preconditions.checkNotNull(id);
        return integralChangeMapper.getIntegralChange(id);
    }

    @Override
    public IntegralChange getIntegralChangeByCode(String code) {
        Preconditions.checkNotNull(code);
        return integralChangeMapper.getIntegralChangeByCode(code);
    }
}
