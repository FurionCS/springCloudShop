package com.spring.domain;

import com.spring.domain.type.TccStatus;
import lombok.*;

import java.time.OffsetDateTime;

/**
 * @Description 产品库存Tcc
 * @Author ErnestCheng
 * @Date 2017/6/1.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductStockTcc {
    /**
     * 产品库存
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
     * 库存
     */
    private Integer stock;
    /**
     * 状态
     */
    private TccStatus status;
    /**
     * 产品id
     */
    private Integer productId;

}
