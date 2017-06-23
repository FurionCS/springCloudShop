package com.spring.persistence;

import com.spring.domain.model.UserRole;
import com.spring.domain.model.VO.UserRoleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description 用户角色
 * @Author ErnestCheng
 * @Date 2017/6/23.
 */
@Repository
public interface UserRoleMapper {
    /**
     * 添加用户角色
     * @param userRole
     * @return
     */
    int addUserRole(@Param("userRole") UserRole userRole);

    /**
     * 通过用户id获得用户可用角色
     * @param userId
     * @return
     */
    UserRoleVO getUserRoleVO(@Param("userId") Integer userId);

}
