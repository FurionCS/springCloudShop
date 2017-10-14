package com.spring.domain.event;


import com.spring.domain.eventBus.DomainEvent;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class UserLoginEvent extends DomainEvent{
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

    @Override
    protected String identify() {
        return "user_integral_detail";
    }
}
