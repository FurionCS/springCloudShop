package com.spring.web;

import com.spring.common.model.StatusCode;
import com.spring.common.model.response.ObjectDataResponse;
import com.spring.domain.model.UserAddr;
import com.spring.service.UserAddrService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

/**
 * 用户地址
 * Created by ErnestCheng on 2017/9/30.
 */
@RestController
@RequestMapping("/user/addr")
public class UserAddrController {

    @Autowired
    private UserAddrService userAddrService;


    @PostMapping("/addUserAddr")
    @ApiOperation("新增用户地址")
    public ObjectDataResponse addUserAddr(@Validated @RequestBody UserAddr userAddr, BindingResult result) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        boolean flag=userAddrService.addUserAddr(userAddr);
        if(flag) {
            return new ObjectDataResponse();
        }else{
            return new ObjectDataResponse(StatusCode.Update_Fail,"添加失败");
        }
    }
}
