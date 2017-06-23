package com.spring.domain.model;

import lombok.Data;

import java.time.OffsetDateTime;

/**
 * @Description 角色
 * @Author ErnestCheng
 * @Date 2017/6/23.
 */
@Data
public class Role {
    /**
     * 角色id
     */
    private int id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态
     */
    private int status;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private OffsetDateTime createTime;
    /**
     * 修改时间
     */
    private OffsetDateTime updateTime;

}
