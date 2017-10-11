package com.spring.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * 用户积分
 * Created by ErnestCheng on 2017/10/11.
 */
@Data
@ToString
@NoArgsConstructor
public class UserIntegral {
    /**
     * 用户积分id，uuid
     */
    private String id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 现有积分
     */
    private Long nowSource;
    /**
     * 历史积分
     */
    private Long hisSource;
    /**
     * 使用过的积分
     */
    private Long usedSource;
}
