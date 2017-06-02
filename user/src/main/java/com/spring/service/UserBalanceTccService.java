package com.spring.service;

import com.spring.domain.model.UserBalanceTcc;

import java.math.BigDecimal;

/**
 * @Description 用户余额tcc
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
public interface UserBalanceTccService {
    /**
     * 尝试预留资源
     * @param userId
     * @param amount
     * @return
     */
    UserBalanceTcc trying(Integer userId, Double amount);

    /**
     * 确认预留资源
     * @param reservationId
     */
    void confirm(Integer reservationId);
}
