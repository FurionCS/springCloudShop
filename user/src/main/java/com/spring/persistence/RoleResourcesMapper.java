package com.spring.persistence;

import com.spring.domain.model.RoleResource;
import com.spring.domain.model.VO.RoleResourcesVO;
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


    /**
     * 获得角色资源
     * @param roleId
     * @return
     */
    RoleResourcesVO getRoleResourcesVO(@Param("roleId") Integer roleId );
}
