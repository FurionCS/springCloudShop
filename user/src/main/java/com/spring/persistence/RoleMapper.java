package com.spring.persistence;

import com.spring.domain.model.Role;
import com.spring.domain.model.VO.RoleResourcesVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:角色
 * @Author : Mr.Cheng
 * @Date:2017/6/23
 */
@Repository
public interface RoleMapper {
    /**
     * 添加角色
     * @param role
     * @return
     */
    int addRole(Role role);

    /**
     * 得到角色
     * @param id
     * @return
     */
    Role getRole(@Param("id") Integer id);

    /**
     * 状态为status的获得所有角色信息，
     * @return
     */
    List<Role> listRole(@Param("status") Integer status);



}
