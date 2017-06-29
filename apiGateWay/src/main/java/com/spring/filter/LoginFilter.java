package com.spring.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.spring.jwt.AuthTokenDetails;
import com.spring.jwt.JsonWebTokenUtility;
import com.spring.model.VO.AuthTokenVO;
import com.sun.istack.internal.logging.Logger;

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

    Logger logger= Logger.getLogger(LoginFilter.class);

    private JsonWebTokenUtility tokenService = new JsonWebTokenUtility();

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
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        final String requestURI = ctx.getRequest().getRequestURI();
        logger.info("requesteUrl:"+requestURI);
        if(requestURI.contains("/user/login")){
            final InputStream responseDataStream = ctx.getResponseDataStream();
            final String responseData;
            try {
                responseData = CharStreams.toString(new InputStreamReader(responseDataStream,"UTF-8"));
                ctx.setResponseBody(responseData);
                Map<String,Object> value = JSONObject.parseObject(responseData,Map.class);
                if(value!=null&&value.get("data")!=null){
                    Map<String,Object> data=JSONObject.parseObject(value.get("data").toString());
                    AuthTokenDetails authTokenDetails = new AuthTokenDetails();
                    authTokenDetails.setId(Long.valueOf(data.get("userId").toString()));
                    authTokenDetails.setUsername(data.get("userName").toString());
                    authTokenDetails.setExpirationDate(buildExpirationDate());
                    JSONArray jsonArray =JSONObject.parseArray(data.get("roles").toString());
                    List<String> roleNameList= jsonArray.stream().map(json->{
                        Map<String,String> map=JSONObject.parseObject(json.toString(),Map.class);
                        return map.get("roleName").toString();
                    }).collect(Collectors.toList());
                    authTokenDetails.setRoleNames(roleNameList);
                    String jwt=tokenService.createJsonWebToken(authTokenDetails);
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
    /**
     * 设定过期时间
     * @return
     */
    private Date buildExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return calendar.getTime();
    }
}
