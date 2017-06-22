package com.spring.web;

import com.spring.domain.model.User;
import com.spring.domain.model.UserAuth;
import com.spring.domain.model.response.LoginResponse;
import com.spring.domain.model.response.ObjectDataResponse;
import com.spring.repository.UserAuthRepository;
import com.spring.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description 权限
 * @Author ErnestCheng
 * @Date 2017/6/22.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @ApiOperation(value="登入")
    @PostMapping(value="/login")
    public ObjectDataResponse<LoginResponse> login(@Valid @RequestBody UserAuth userAuth, BindingResult result){
        ObjectDataResponse objectDataResponse=new ObjectDataResponse();
        UserAuth userAuth1=userAuthRepository.findByUserName(userAuth.getUserName());
        if(userAuth!=null) {
            if (userAuth.getPassword().equals(userAuth1.getPassword())) {

            }
        }
        return null;
    }
}
