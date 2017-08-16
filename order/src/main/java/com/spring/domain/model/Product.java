package com.spring.domain.model;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 产品
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable{
    /**
     * id
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除时间
     */
    private Date deleteTime;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 价格
     */
    private BigDecimal price;
}
