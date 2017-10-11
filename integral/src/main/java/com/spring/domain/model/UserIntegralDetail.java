package com.spring.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * 用户积分详情
 * Created by ErnestCheng on 2017/10/11.
 */
@Data
@ToString
@NoArgsConstructor
public class UserIntegralDetail {
    /**
     * 用户积分详情id uuid
     */
    private String id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 更新时间
     */
    private Timestamp createTime;
    /**
     * 积分改变多少
     */
    private Integer changeSource;
    /**
     * 备注
     */
    private String remark;
    /**
     * 积分改变类型
     */
    private Integer changeType;

}
