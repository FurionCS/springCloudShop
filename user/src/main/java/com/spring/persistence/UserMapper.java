package com.spring.persistence;

import com.spring.domain.model.User;
import com.spring.domain.model.type.UserStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

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
     * 通过名称获得用户
     * @param userName
     * @return
     */
    User getUserByName(@Param("userName") String userName);

    /**
     * 消费余额
     * @param userId
     * @param amount
     * @return
     */
    int consumeBalance(@Param("usreId") Integer userId,@Param("amount") BigDecimal amount);

    /**
     * 更新用户余额
     * @param userId
     * @param amount
     * @return
     */
    int updateBalance(@Param("userId") Integer userId,@Param("amount") BigDecimal amount);

    /**
     * 更新用户信息
     * @param userId
     * @param  userName
     * @param idCard
     * @return
     */
    int updateUser(@Param("userId") Integer userId,@Param("userName") String userName,@Param("idCard") String idCard,@Param("password")String password);

    /**
     * 通过用户id删除用户
     * @param userId
     * @return
     */
    int deleteUserByUserId(@Param("userId") Integer userId);

    /**
     * 获得用户列表
     * @param status
     * @param startIndex
     * @param endIndex
     * @param startDate
     * @param endDate
     * @return
     */
    List<User> findUser(@Param("status") UserStatus status,
                        @Param("startIndex") Integer startIndex,
                        @Param("endIndex") Integer endIndex,
                        @Param("startDate") String startDate,
                        @Param("endDate") String endDate
                        );
}
