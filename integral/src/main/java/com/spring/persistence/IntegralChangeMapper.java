package com.spring.persistence;

import com.spring.domain.model.IntegralChange;
import com.spring.domain.type.IntegralChangeStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
@Repository
public interface IntegralChangeMapper {
    /**
     * 添加积分规则
     * @param integralChange
     * @return
     */
    Integer addIntegralChange(IntegralChange integralChange);

    /**
     * 获得积分规则列表
     * @param integralChangeStatus
     * @return
     */
    List<IntegralChange> listIntegralChange(IntegralChangeStatus integralChangeStatus);

    /**
     * 更改积分规则
     * @param integralChange
     * @return
     */
    Integer updateIntegralChange(IntegralChange integralChange);

    /**
     * 获得积分规则
     * @param id
     * @return
     */
    IntegralChange getIntegralChange(int id);
}
