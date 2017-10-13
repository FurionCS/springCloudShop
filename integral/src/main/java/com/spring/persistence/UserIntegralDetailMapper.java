package com.spring.persistence;

import com.spring.domain.model.UserIntegralDetail;
import org.springframework.stereotype.Repository;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
@Repository
public interface UserIntegralDetailMapper {
    /**
     * 新增用户积分详情
     * @param userIntegralDetail
     * @return
     */
    Integer addUserIntegralDetail(UserIntegralDetail userIntegralDetail);
}
