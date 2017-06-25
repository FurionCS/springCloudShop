package com.spring.persistence;

import com.spring.domain.model.RoleResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:${Description}
 * @Author : Mr.Cheng
 * @Date:2017/6/24
 */
@Repository
public interface RoleResourcesMapper {
    /**
     * 添加角色资源
     * @param roleResource
     * @return
     */
    int addRoleResource(@Param("RoleResource")RoleResource roleResource);

    int addRoleResources(@Param("RoleResources")List<RoleResource> roleResourceList);
}
