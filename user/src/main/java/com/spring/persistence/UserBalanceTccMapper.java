package com.spring.persistence;

import com.spring.domain.model.UserBalanceTcc;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

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

    /**
     * 获得过期资源
     * @param limitation
     * @return
     */
    Set<UserBalanceTcc> selectExpireReservation(@Param("limitation") Integer limitation);

    /**
     * 删除资源
     * @param id
     * @return
     */
    int deleteReservation(@Param("id") Integer id);
}
