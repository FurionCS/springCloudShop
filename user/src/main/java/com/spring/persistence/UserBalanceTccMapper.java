package com.spring.persistence;

import com.spring.domain.model.UserBalanceTcc;
import org.springframework.stereotype.Repository;

/**
 * @Description 用户余额TccMapper
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Repository
public interface UserBalanceTccMapper {
    /**
     * 添加用户余额Tcc
     * @param userBalanceTcc
     * @return
     */
    int addUserBalanceTcc(UserBalanceTcc userBalanceTcc);
}
