package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.domain.model.Role;
import com.spring.domain.model.RoleResource;
import com.spring.persistence.RoleMapper;
import com.spring.persistence.RoleResourcesMapper;
import com.spring.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:角色
 * @Author : Mr.Cheng
 * @Date:2017/6/24
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourcesMapper roleResourcesMapper;

    @Override
    public void addRole(Role role) {
        Preconditions.checkNotNull(role);
        role.setCreateTime(OffsetDateTime.now());
        role.setStatus(1);
        roleMapper.addRole(role);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRoleAndResources(Role role, List<Integer> resourcesIds) {
        //添加角色
        this.addRole(role);
        //添加角色资源
        if(resourcesIds.size()>0) {
            List<RoleResource> roleResources = new ArrayList<>();
            resourcesIds.forEach(resourcesId->{
                RoleResource roleResource=new RoleResource();
                roleResource.setRoleId(role.getId());
                roleResource.setResourceId(resourcesId);
                roleResource.setStatus(1);
                roleResource.setCreateTime(OffsetDateTime.now());
                roleResources.add(roleResource);
            });
            roleResourcesMapper.addRoleResources(roleResources);
        }
    }
}
