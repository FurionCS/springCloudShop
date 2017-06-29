package com.spring.service;

import com.spring.domain.model.Resource;
import com.spring.domain.model.Role;

import java.util.List;

/**
 * @Description 资源
 * @Author ErnestCheng
 * @Date 2017/6/26.
 */
public interface ResourcesService {
    /**
     * 添加资源
     * @param resource
     * @return
     */
    int addResource(Resource resource);

    /**
     * 获得状态为status的资源
     * @param status
     * @return
     */
    List<Resource> listResources(Integer status);

    /**
     * 通过资源id获得角色名称
     * @param id
     * @return
     */
    List<String> listRoleByResourceId(Integer id);
}
