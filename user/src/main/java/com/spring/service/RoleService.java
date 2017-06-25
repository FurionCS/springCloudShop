package com.spring.service;

import com.spring.domain.model.Role;
import com.spring.domain.model.request.RoleResourcesRequest;

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
     * t添加角色资源
     * @param role
     * @param resourcesIds
     */
     void addRoleAndResources(Role role, List<Integer> resourcesIds);
}
