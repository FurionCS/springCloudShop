package com.spring.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.spring.jwt.AuthTokenDetails;
import com.spring.jwt.JsonWebTokenUtility;
import com.spring.model.vo.AuthTokenVO;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @Description 登入过滤器
 * 登录后获得登录信息，生成token
 * @Author ErnestCheng
 * @Date 2017/6/26.
 */
public class LoginFilter extends ZuulFilter {

    private  static  final  Logger logger= Logger.getLogger(LoginFilter.class);

    @Autowired
    private JsonWebTokenUtility tokenService;

    @Value("${jwt.user.login:/user/login}")
    private String loginUrl;

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public final Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        final String requestURI = ctx.getRequest().getRequestURI();
        if(requestURI.contains(loginUrl)){
            final InputStream responseDataStream = ctx.getResponseDataStream();
            final String responseData;
            try {
                responseData = CharStreams.toString(new InputStreamReader(responseDataStream,"UTF-8"));
                ctx.setResponseBody(responseData);
                Map<String,Object> value = JSONObject.parseObject(responseData,Map.class);
                if(value!=null&&value.get("data")!=null){
                    Map<String,Object> data=JSONObject.parseObject(value.get("data").toString());
                    JSONArray jsonArray =JSONObject.parseArray(data.get("roles").toString());
                    List<String> roleNameList= jsonArray.stream().map(json->{
                        Map<String,String> map=JSONObject.parseObject(json.toString(),Map.class);
                        return map.get("roleName");
                    }).collect(Collectors.toList());
                    AuthTokenDetails authTokenDetails = new AuthTokenDetails(Long.valueOf(data.get("userId").toString()),data.get("userName").toString(),null,roleNameList,null);
                    String jwt=tokenService.initKey().createJsonWebToken(authTokenDetails);
                    authTokenDetails=null;
                    if(jwt!=null){
                        AuthTokenVO authTokenVO=new AuthTokenVO();
                        authTokenVO.setToken(jwt);
                        authTokenVO.setUserId(Long.valueOf(data.get("userId").toString()));
                        ctx.setResponseBody(JSON.toJSON(authTokenVO).toString());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
