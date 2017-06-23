package com.spring.domain.model;

import lombok.Data;

import java.time.OffsetDateTime;

/**
 * @Description 资源
 * @Author ErnestCheng
 * @Date 2017/6/23.
 */
@Data
public class Resource {
    /**
     * 资源id
     */
    private int id;
    /**
     * 资源链接
     */
    private String url;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态 0:停用，1:正常
     */
    private int status;
    /**
     *创建用户
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
