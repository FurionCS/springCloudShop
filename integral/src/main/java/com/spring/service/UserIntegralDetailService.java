package com.spring.service;

import com.spring.domain.model.UserIntegralDetail;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
public interface UserIntegralDetailService {
    /**
     * 新增用户积分详情
     * @return
     */
    Integer addUserIntegralDetail(UserIntegralDetail userIntegralDetail);
}
