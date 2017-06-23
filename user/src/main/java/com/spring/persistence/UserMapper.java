package com.spring.persistence;

import com.spring.domain.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description 用户数据接口
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Repository
public interface UserMapper {
    /**
     * 添加用户信息
     * @param user
     */
    void addUser(User user);

    /**
     * 通过用户id获得用户
     * @param userId
     * @return
     */
    User getUserById(@Param("userId") Integer userId);

    /**
     * 消费余额
     * @param userId
     * @param amount
     * @return
     */
    int consumeBalance(@Param("usreId") Integer userId,@Param("amount") Double amount);

    /**
     * 更新用户余额
     * @param userId
     * @param amount
     * @return
     */
    int updateBalance(@Param("userId") Integer userId,@Param("amount") Double amount);

}
