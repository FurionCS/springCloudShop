package com.spring.service;

import com.spring.domain.model.Resource;

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
}
