package com.spring.web;

import com.spring.common.model.StatusCode;
import com.spring.domain.model.User;
import com.spring.domain.model.response.UserResponse;
import com.spring.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 用户Controller
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@RestController
public class UserController {

    Logger logger= Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value="添加用户")
    @PostMapping(value="addUser")
    public UserResponse addUser(@RequestBody User user){
        UserResponse userResponse=new UserResponse();
        if(user==null|| "".equals(user.getUserName())||"".equals(user.getPassword())){
            userResponse.setCode(StatusCode.Fail_Code);
            userResponse.setMessage("参数不正确");
        }
        logger.info(user);
        userService.addUser(user);
        return userResponse;
    }
}
