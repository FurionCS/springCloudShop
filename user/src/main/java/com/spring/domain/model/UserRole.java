package com.spring.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * @Description 用户角色
 * @Author ErnestCheng
 * @Date 2017/6/23.
 */
@Data
@NoArgsConstructor
@Builder
public class UserRole {
    /**
     * 用户角色id
     */
    private int id;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 角色id
     */
    private int roleId;
    /**
     * 状态 0:停用 1:正常
     */
    private int status;
    /**
     * 创建时间
     */
    private OffsetDateTime createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改时间
     */
    private OffsetDateTime updateTime;


}

