package com.spring.service;

import com.spring.domain.model.Role;
import com.spring.domain.model.vo.RoleResourcesVO;

import java.util.List;

/**
 * @Description:角色
 * @Author : Mr.Cheng
 * @Date:2017/6/24
 */
public interface RoleService {
    /**
     * 添加角色
     * @param role
     */
    void addRole(Role role);

    /**
     * 添加角色资源
     * @param role
     * @param resourcesIds
     */
     void addRoleAndResources(Role role, List<Integer> resourcesIds);

    /**
     *获得角色资源
     * @param roleId
     * @return
     */
    RoleResourcesVO getRoleResourcesVo(Integer roleId);

    /**
     * 获得状态为status的所有角色信息
     * @param status
     * @return
     */
    List<Role> listRole(Integer status);
}
