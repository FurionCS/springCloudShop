package com.spring.persistence;

import com.spring.domain.model.User;
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

    /**
     * 得到预留资源通过id
     * @param id
     * @return
     */
    UserBalanceTcc getUserBalanceTcc(Integer id);

    /**
     * 更新预留资源状态
     * @param id
     * @return
     */
    int updateUserBalanceTccStatus(Integer id);
}
