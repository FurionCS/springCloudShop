package com.spring.persistence;

import com.spring.domain.model.UserIntegral;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
@Repository
public interface UserIntegralMapper {
    /**
     * 用户积分
     * @param userId
     * @return
     */
    UserIntegral getUserIntegral(Integer userId);

    /**
     * 更新用户积分
     * @param userId
     * @param nowSource
     * @param hisSource
     * @param usedSource
     * @return
     */
    Integer updateUserIntegral(@Param("userId") Integer userId,@Param("nowSource") Long nowSource,@Param("hisSource") Long hisSource,@Param("usedSource") Long usedSource);

    /**
     * 新增用户积分
     * @param userIntegral
     * @return
     */
    Integer addUserIntegral(UserIntegral userIntegral);
}
