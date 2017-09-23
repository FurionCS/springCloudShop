package com.spring.domain.model;

import lombok.*;

import java.time.OffsetDateTime;

/**
 * @Description 预留资源
 * @Author ErnestCheng
 * @Date 2017/6/1.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderParticipant {
    /**
     * 预留资源
     */
    private Integer id;
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
     * 过期时间
     */
    private OffsetDateTime expireTime;
    /**
     * 预留资源uri
     */
    private String uri;
    /**
     * 订单id
     */
    private Integer orderId;


    public OrderParticipant(OffsetDateTime createTime, OffsetDateTime updateTime, OffsetDateTime deleteTime, OffsetDateTime expireTime, String uri, Integer orderId) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteTime = deleteTime;
        this.expireTime = expireTime;
        this.uri = uri;
        this.orderId = orderId;
    }
}
