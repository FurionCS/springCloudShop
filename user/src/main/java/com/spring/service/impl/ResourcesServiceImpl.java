package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.domain.model.Resource;
import com.spring.persistence.ResourcesMapper;
import com.spring.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

/**
 * @Description 资源
 * @Author ErnestCheng
 * @Date 2017/6/26.
 */
@Service
public class ResourcesServiceImpl implements ResourcesService{

    @Autowired
    private ResourcesMapper resourcesMapper;
    @Override
    public int addResource(Resource resource) {
        Preconditions.checkNotNull(resource);
        resource.setCreateTime(OffsetDateTime.now());
        return resourcesMapper.addResource(resource);
    }
}
