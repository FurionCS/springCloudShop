package com.spring.sercuity;

import com.spring.model.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Description 资源对应权限
 * @Author ErnestCheng
 * @Date 2017/6/23.
 */
@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    private Logger logger= Logger.getLogger(MyInvocationSecurityMetadataSourceService.class);
    @Autowired
    private RestTemplate restTemplate;

    @Value("${resources.list.url}")
    private String resourcesUrl;

    @Value("${resources.list.roleName}")
    private String roleNameUrl;
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public MyInvocationSecurityMetadataSourceService() {

    }
    private void loadResourceDefine() {
        /*
         * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
         * sparta
         */
        List<HashMap> resources = restTemplate.getForObject(resourcesUrl,List.class); //
        logger.info(resources);
        resourceMap = new HashMap<>();
        for (HashMap resource : resources) {
            // 查询每个资源对于的角色
            MultiValueMap<String, Integer> requestEntity = new LinkedMultiValueMap<>();
            requestEntity.add("id",Integer.valueOf(resource.get("id").toString()));
            List<String> roleNames = restTemplate.postForObject(roleNameUrl,requestEntity,List.class);
            logger.info(roleNames);
            Collection<ConfigAttribute> value=new ArrayList<>();
            if(roleNames!=null && roleNames.size()>0) {
                for (String roleName : roleNames) {
                    ConfigAttribute ca = new SecurityConfig(roleName);
                    value.add(ca);
                }
            }else {
                    ConfigAttribute ca = new SecurityConfig("super");
                    value.add(ca);
            }
            resourceMap.put(resource.get("url").toString(), value);
        }
    }
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        for (String url : resourceMap.keySet()) {
            RequestMatcher requestMatcher = new AntPathRequestMatcher(url);
            HttpServletRequest httpRequest = filterInvocation.getHttpRequest();

            if (requestMatcher.matches(httpRequest)) {
                return resourceMap.get(url);
            }
        }
        return null;
    }
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        loadResourceDefine();
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
