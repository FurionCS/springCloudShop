package com.spring.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.domain.type.TccStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
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
    private BigDecimal amount;
    /**
     * 状态
     */
    private TccStatus status;
    /**
     * 用户id
     */
    private Integer userId;

}