package com.spring.service;

import com.spring.domain.model.IntegralChange;
import com.spring.domain.type.IntegralChangeStatus;

import java.util.List;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
public interface IntegralChangeService {
    /**
     * 新增积分规则
     * @param integralChange
     * @return
     */
   Integer  addIntegralChange(IntegralChange integralChange);

    /**
     * 获得积分规则列表
     * @param integralChangeStatus
     * @return
     */
   List<IntegralChange> listIntegralChange(IntegralChangeStatus integralChangeStatus);

    /**
     * 更新积分规则
     * @param integralChange
     * @return
     */
   Integer updateIntegral(IntegralChange integralChange);

    /**
     * 获得积分规则
     * @param id
     * @return
     */
   IntegralChange getIntegralChange(Integer id);

    /**
     * 获得积分规则通过编码
     * @param code
     * @return
     */
    IntegralChange getIntegralChangeByCode(String code);
}
