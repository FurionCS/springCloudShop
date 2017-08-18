package com.spring.web;

import com.google.common.base.Preconditions;
import com.spring.common.model.StatusCode;
import com.spring.common.model.exception.GlobalException;
import com.spring.common.model.util.tools.SecurityUtil;
import com.spring.domain.model.User;
import com.spring.domain.model.UserAuth;
import com.spring.domain.model.VO.UserRoleVO;
import com.spring.domain.model.request.UserPasswordRequest;
import com.spring.domain.model.request.UserRequest;
import com.spring.domain.model.request.UserRoleRequest;
import com.spring.domain.model.request.UserUpdateRequest;
import com.spring.domain.model.response.ObjectDataResponse;
import com.spring.domain.model.response.UserResponse;
import com.spring.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
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
    public UserResponse login(@Validated @RequestBody UserRequest userRequest, BindingResult result) {
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
                    return new UserResponse(userRoleVO);
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
    public UserResponse addUser(@Valid @RequestBody User user, BindingResult result) {
        UserResponse userResponse = new UserResponse();
        userService.addUser(user);
        return userResponse;
    }

    @ApiOperation(value = "添加用户和用户角色")
    @PostMapping(value = "addUserAndRole")
    public UserResponse addUserAndRole(@Validated @RequestBody UserRoleRequest userRoleRequest, BindingResult result) {
        UserResponse userResponse = new UserResponse();
        User user = userRoleRequest.getUser();
        List<Integer> roleIds = userRoleRequest.getRoleIds();
        user.setCreateTime(new Date());
        userService.addUserAndRole(user, roleIds);
        return userResponse;
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
    public UserResponse updateUser(@Validated @RequestBody UserUpdateRequest userUpdateRequest, BindingResult result) {
        UserResponse userResponse = new UserResponse();
        int flag = userService.updateUser(userUpdateRequest);
        if (flag <= 0) {
            userResponse.setCode(StatusCode.Update_Fail);
            userResponse.setMessage("更新失败");
        }
        return userResponse;
    }

    @ApiOperation(value = "删除用户")
    @PostMapping(value = "/deleteUserByUserId")
    public UserResponse deleteUserByUserId(@RequestParam Integer userId) {
        UserResponse userResponse = new UserResponse();
        int flag = userService.deleteUserByUserId(userId);
        if (flag == 0) {
            userResponse.setCode(StatusCode.Fail_Code);
            userResponse.setMessage("删除失败");
        }
        return userResponse;
    }


    @ApiOperation(value = "更新用户密码")
    @PostMapping(value = "/updatePassword")
    public UserResponse updatePassword(@Validated @RequestBody UserPasswordRequest userPasswordRequest, BindingResult result) {
        UserResponse userResponse = new UserResponse();
        User user = userService.getUserById(userPasswordRequest.getUserId());
        if (user == null) {
            userResponse.setCode(StatusCode.Data_Not_Exist);
            userResponse.setMessage("找不到该用户");
            return userResponse;
        }
        int flag = userService.updatePassword(userPasswordRequest.getNewPassword(), userPasswordRequest.getOldPassword(), user);
        if (flag == 0) {
            userResponse.setCode(StatusCode.Update_Fail);
            userResponse.setMessage("更新失败");
        }
        return userResponse;
    }

}
