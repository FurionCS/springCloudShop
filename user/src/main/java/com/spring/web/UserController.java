package com.spring.web;

import com.spring.common.model.StatusCode;
import com.spring.common.model.exception.GlobalException;
import com.spring.common.model.response.ObjectDataResponse;
import com.spring.common.model.util.tools.SecurityUtil;
import com.spring.domain.model.User;
import com.spring.domain.model.VO.UserRoleVO;
import com.spring.domain.model.request.UserPasswordRequest;
import com.spring.domain.model.request.UserRequest;
import com.spring.domain.model.request.UserRoleRequest;
import com.spring.domain.model.request.UserUpdateRequest;
import com.spring.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * @Description 用户Controller
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@RestController
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("用户登入")
    @PostMapping(value = "/login")
    public ObjectDataResponse login(@Validated @RequestBody UserRequest userRequest, BindingResult result) {
        String userName = userRequest.getUserName();
        String password = userRequest.getPassword();
        if ("".equals(userName) || "".equals(password)) {
            throw new GlobalException("参数不对");
        } else {
            User user = userService.getUserByName(userName);
            try {
                String password1 = SecurityUtil.md5(userName, password, 32);
                if (user != null && user.getPassword().equals(password1)) {
                    UserRoleVO userRoleVO = userService.listUserRoleVO(user.getId());
                    return new ObjectDataResponse(userRoleVO);
                } else {
                    throw new GlobalException("用户密码不正确");
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @ApiOperation(value = "添加用户")
    @PostMapping(value = "addUser")
    public ObjectDataResponse addUser(@Valid @RequestBody User user, BindingResult result) {
        userService.addUser(user);
        return new ObjectDataResponse();
    }

    @ApiOperation(value = "添加用户和用户角色")
    @PostMapping(value = "addUserAndRole")
    public ObjectDataResponse addUserAndRole(@Validated @RequestBody UserRoleRequest userRoleRequest, BindingResult result) {
        User user = userRoleRequest.getUser();
        List<Integer> roleIds = userRoleRequest.getRoleIds();
        user.setCreateTime(new Date());
        userService.addUserAndRole(user, roleIds);
        return new ObjectDataResponse();
    }

    @ApiOperation(value = "获得用户")
    @RequestMapping(value = "getUserById", method = RequestMethod.GET)
    public ObjectDataResponse<User> getUserById(@RequestParam Integer userId) {
        ObjectDataResponse objectDataResponse = new ObjectDataResponse();
        if (userId == null || userId < 1) {
            objectDataResponse.setCode(StatusCode.Param_Error);
            objectDataResponse.setMessage("参数不对");
            return objectDataResponse;
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            objectDataResponse.setCode(StatusCode.Data_Not_Exist);
            objectDataResponse.setMessage("数据不存在");
        } else {
            objectDataResponse.setData(user);
        }
        return objectDataResponse;
    }

    @ApiOperation(value = "获得用户角色列表")
    @GetMapping(value = "/listUserRole")
    public UserRoleVO listUserRoleVO(@RequestParam Integer userId) {
        if (userId == null || userId < 1) {
            return new UserRoleVO();
        }
        return userService.listUserRoleVO(userId);
    }


    @ApiOperation(value = "更新用户信息")
    @PostMapping(value = "/updateUser")
    public ObjectDataResponse updateUser(@Validated @RequestBody UserUpdateRequest userUpdateRequest, BindingResult result) {
        int flag = userService.updateUser(userUpdateRequest);
        if (flag <= 0) {
           return new ObjectDataResponse(StatusCode.Update_Fail,"更新失败");
        }
        return new ObjectDataResponse();
    }

    @ApiOperation(value = "删除用户")
    @PostMapping(value = "/deleteUserByUserId")
    public ObjectDataResponse deleteUserByUserId(@RequestParam Integer userId) {
        int flag = userService.deleteUserByUserId(userId);
        if (flag == 0) {
            return new ObjectDataResponse(StatusCode.Update_Fail,"删除失败");
        }
        return new ObjectDataResponse();
    }


    @ApiOperation(value = "更新用户密码")
    @PostMapping(value = "/updatePassword")
    public ObjectDataResponse updatePassword(@Validated @RequestBody UserPasswordRequest userPasswordRequest, BindingResult result) {
        User user = userService.getUserById(userPasswordRequest.getUserId());
        if (user == null) {
            return new ObjectDataResponse(StatusCode.Data_Not_Exist,"用户不存在");
        }
        int flag = userService.updatePassword(userPasswordRequest.getNewPassword(), userPasswordRequest.getOldPassword(), user);
        if (flag == 0) {
           return new ObjectDataResponse(StatusCode.Update_Fail,"更新失败");
        }
        return new ObjectDataResponse();
    }

}
