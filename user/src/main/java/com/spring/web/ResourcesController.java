package com.spring.web;

import com.google.common.base.Preconditions;
import com.spring.common.model.StatusCode;
import com.spring.domain.model.Resource;
import com.spring.domain.model.response.ObjectDataResponse;
import com.spring.service.ResourcesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 资源
 * @Author ErnestCheng
 * @Date 2017/6/26.
 */
@RestController
@RequestMapping("/resources")
public class ResourcesController {

    @Autowired
    private ResourcesService resourcesService;

    @ApiOperation(value="添加资源")
    @PostMapping("/addResource")
    public ObjectDataResponse<Resource> addResource(@Valid @RequestBody Resource resource, BindingResult result){
        Preconditions.checkNotNull(resource);
        ObjectDataResponse objectDataResponse=new ObjectDataResponse();
        int i=resourcesService.addResource(resource);
        if(i!=1){
            objectDataResponse.setMessage("添加失败");
            objectDataResponse.setCode(StatusCode.API_Fail);
        }
        return objectDataResponse;
    }

    @ApiOperation(value="获得状态为status的资源")
    @GetMapping("/listResources")
    public List<Resource> listResource(@RequestParam Integer status){
        Preconditions.checkNotNull(status);
        ObjectDataResponse objectDataResponse=new ObjectDataResponse();
        List<Resource> resourceList=resourcesService.listResources(status);
        return resourceList;
    }

    @ApiOperation("通过资源获得角色名称")
    @PostMapping("/listRoleNameByResourceId")
    public List<String> listRoleNameByResourceId(@RequestParam("id") Integer id){
        List<String> roleNameList=resourcesService.listRoleByResourceId(id);
        return roleNameList;
    }
}
