package com.spring.web;

import com.spring.model.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description test
 * @Author ErnestCheng
 * @Date 2017/6/26.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    Logger logger= Logger.getLogger(TestController.class);
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/rest")
    public void testRest(){
        List<Role> roles = restTemplate.getForObject("http://USER/role/listRole?status=1",List.class); // 替换为查询角色列表
        logger.info(roles);
        Map<String, Object> params = new HashMap<>();
        params.put("roleId", 3);
        // 查询每个角色对于的权限,我这里假设直接查到了url
        List<String> resources = restTemplate.getForObject("http://USER/role/listRoleResources",List.class,params);
        logger.info(resources);
    }
}
