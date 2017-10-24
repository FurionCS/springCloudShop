package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.common.model.exception.GlobalException;
import com.spring.domain.model.Resource;
import com.spring.domain.model.Role;
import com.spring.domain.model.RoleResource;
import com.spring.domain.vo.RoleResourcesVO;
import com.spring.persistence.ResourcesMapper;
import com.spring.persistence.RoleMapper;
import com.spring.persistence.RoleResourcesMapper;
import com.spring.service.RoleService;
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

    @Autowired
    private ResourcesMapper resourcesMapper;

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
        if(!resourcesIds.isEmpty()) {
            List<RoleResource> roleResources = new ArrayList<>();
            resourcesIds.forEach(resourcesId->{
               Resource resource=resourcesMapper.getResource(resourcesId);
                if(resource!=null) {
                    RoleResource roleResource = new RoleResource();
                    roleResource.setRoleId(role.getId());
                    roleResource.setResourceId(resourcesId);
                    roleResource.setStatus(1);
                    roleResource.setCreateTime(OffsetDateTime.now());
                    roleResources.add(roleResource);
                }else{
                    throw new GlobalException("找不到id为"+resourcesId+"的资源");
                }
            });
            roleResourcesMapper.addRoleResources(roleResources);
        }
    }

    @Override
    public RoleResourcesVO getRoleResourcesVo(Integer roleId) {
        Preconditions.checkArgument(roleId>0);
        return roleResourcesMapper.getRoleResourcesVO(roleId);
    }

    @Override
    public List<Role> listRole(Integer status) {
        return roleMapper.listRole(status);
    }
}
