package com.spring.domain.model;

import lombok.Data;

import java.time.OffsetDateTime;

/**
 * @Description 角色资源
 * @Author ErnestCheng
 * @Date 2017/6/23.
 */
@Data
public class RoleResource {
    /**
     * 角色资源id
     */
    private int id;
    /**
     * 角色id
     */
    private int roleId;
    /**
     * 资源id
     */
    private int resourceId;
    /**
     * 状态 0:停用，1:正常
     */
    private int status;
    /**
     * 创建时间
     */
    private OffsetDateTime createTime;
    /**
     * 修改时间
     */
    private OffsetDateTime updateTime;
    /**
     * 创建人
     */
    private String createUser;

}
