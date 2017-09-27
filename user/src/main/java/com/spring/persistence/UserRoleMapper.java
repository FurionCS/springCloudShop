package com.spring.persistence;

import com.spring.domain.model.UserRole;
import com.spring.domain.model.vo.UserRoleVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 添加用户角色列表信息
     * @param userRoleList
     * @return
     */
    int addUserRoleList(@Param("userRoles") List<UserRole> userRoleList);

    /**
     * 通过用户id获得用户可用角色
     * @param userId
     * @return
     */
    UserRoleVO getUserRoleVO(@Param("userId") Integer userId);

}
