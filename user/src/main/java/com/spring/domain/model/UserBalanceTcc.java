package com.spring.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.domain.model.type.TccStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBalanceTcc{
    private static final long serialVersionUID = 8291022579522014250L;
    private Integer id;
    /**
     * 过期时间
     */
    private OffsetDateTime expireTime;
    /**
     * 创建时间
     */
    private OffsetDateTime createTime;
    /**
     * 更新时间
     */
    private OffsetDateTime updateTime;
    /**
     * 删除时间
     */
    private OffsetDateTime deleteTime;
    /**
     * 金额
     */
    private Double amount;
    /**
     * 状态
     */
    private TccStatus status;
    /**
     * 用户id
     */
    private Integer userId;

}