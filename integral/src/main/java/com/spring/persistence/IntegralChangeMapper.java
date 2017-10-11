package com.spring.persistence;

import com.spring.domain.model.IntegralChange;
import org.springframework.stereotype.Repository;

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
}
