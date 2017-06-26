package com.spring.web;

import com.google.common.base.Preconditions;
import com.spring.common.model.StatusCode;
import com.spring.domain.model.Resource;
import com.spring.domain.model.response.ObjectDataResponse;
import com.spring.service.ResourcesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    private ObjectDataResponse<Resource> addResource(@Valid @RequestBody Resource resource, BindingResult result){
        Preconditions.checkNotNull(resource);
        ObjectDataResponse objectDataResponse=new ObjectDataResponse();
        int i=resourcesService.addResource(resource);
        if(i!=1){
            objectDataResponse.setMessage("添加失败");
            objectDataResponse.setCode(StatusCode.API_Fail);
        }
        return objectDataResponse;
    }
}
