
package com.spring.jwt;



import com.spring.model.StatusCode;
import com.spring.model.response.RestfulResponse;
import com.spring.util.JsonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by YangFan on 2016/11/28 下午1:31.
 * <p/>
 */
public class NoAuthenticationEntryPoint implements AuthenticationEntryPoint {

    //当访问的资源没有权限，会调用这里
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        //返回json形式的错误信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        RestfulResponse restResp = new RestfulResponse("", StatusCode.Invalid_Token_ReLogin, "没有登录或登录已过期!");
        response.getWriter().println(JsonUtil.toJsonString(restResp));
        response.getWriter().flush();
    }
}