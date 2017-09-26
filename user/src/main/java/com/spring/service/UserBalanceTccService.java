package com.spring.service;

import com.spring.domain.model.UserBalanceTcc;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
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
    UserBalanceTcc trying(Integer userId, BigDecimal amount) throws InvocationTargetException, IntrospectionException, InstantiationException, IllegalAccessException;

    /**
     * 确认预留资源
     * @param reservationId
     */
    void confirm(Integer reservationId);

    /**
     * 取消预留资源
     * @param id
     * @return
     */
    void cancelReservation(Integer id);
    /**
     * 取消预留资源
     * @param userBalanceTcc
     */
    void cancelReservation(UserBalanceTcc userBalanceTcc);
}
