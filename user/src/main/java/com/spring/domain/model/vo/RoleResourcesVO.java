package com.spring.domain.model.vo;

import com.spring.domain.model.Resource;
import lombok.Data;

import java.util.List;

/**
 * @Description 角色资源VO
 * @Author ErnestCheng
 * @Date 2017/6/26.
 */
@Data
public class RoleResourcesVO {
    /**
     * 角色id
     */
    private int roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 资源列表
     */
    private List<Resource> resources;
}
