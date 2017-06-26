package com.spring.web;

import com.google.common.base.Preconditions;
import com.spring.domain.model.Role;
import com.spring.domain.model.VO.RoleResourcesVO;
import com.spring.domain.model.request.RoleResourcesRequest;
import com.spring.domain.model.response.ObjectDataResponse;
import com.spring.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description:角色
 * @Author : Mr.Cheng
 * @Date:2017/6/23
 */
@RestController
@RequestMapping(value="/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value="添加角色，添加资源")
    @PostMapping(value="/addRoleAndResources")
    public ObjectDataResponse<Role> addRoleAndResources(@RequestBody RoleResourcesRequest roleResourcesRequest){
        Preconditions.checkNotNull(roleResourcesRequest);
        Preconditions.checkNotNull(roleResourcesRequest.getRole());
        ObjectDataResponse objectDataResponse=new ObjectDataResponse();
        roleService.addRoleAndResources(roleResourcesRequest.getRole(),roleResourcesRequest.getResourcesIds());
        return objectDataResponse;
    }

    @ApiOperation(value="获得角色资源")
    @GetMapping(value="/getRoleResources")
    public ObjectDataResponse<RoleResourcesVO> getRoleResources(@RequestParam Integer roleId){
        Preconditions.checkArgument(roleId>0);
        ObjectDataResponse objectDataResponse=new ObjectDataResponse<>();
        RoleResourcesVO  roleResourcesVO=roleService.getRoleResourcesVo(roleId);
        objectDataResponse.setData(roleResourcesVO);
        return objectDataResponse;
    }
}
