package com.spring.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginEvent {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 积分变化编码
     */
    private String code;
    /**
     * 参数变量
     */
    private BigDecimal num;
}
