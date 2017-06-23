package com.spring.web;

import com.spring.domain.model.User;
import com.spring.domain.model.UserAuth;
import com.spring.domain.model.response.LoginResponse;
import com.spring.domain.model.response.ObjectDataResponse;
import com.spring.repository.UserAuthRepository;
import com.spring.service.AuthService;
import com.spring.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @Description 权限
 * @Author ErnestCheng
 * @Date 2017/6/22.
 */
@RestController
public class AuthController {


    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "登入")
    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ObjectDataResponse<String> createAuthenticationToken(
            @RequestBody UserAuth serAuth) throws AuthenticationException {
        ObjectDataResponse objectDataResponse=new ObjectDataResponse();
        final String token = authService.login(serAuth.getUserName(), serAuth.getPassword());
        // Return the token
        objectDataResponse.setData(token);
        return objectDataResponse;
    }

    @ApiOperation(value="刷新")
    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ObjectDataResponse refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        ObjectDataResponse objectDataResponse=new ObjectDataResponse();
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            objectDataResponse.setMessage("刷新失败");
            return objectDataResponse;
        } else {
            objectDataResponse.setData(refreshedToken);
            return objectDataResponse;
        }
    }

    @ApiOperation(value="注册")
    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public UserAuth register(@RequestBody UserAuth userAuth) throws AuthenticationException{
        return authService.register(userAuth);
    }
}
