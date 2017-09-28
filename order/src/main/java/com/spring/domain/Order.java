package com.spring.domain;

import com.spring.domain.type.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 订单
 * @Author ErnestCheng
 * @Date 2017/5/26.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;
    private int userId;
    private int productId;
    private BigDecimal price;
    private OrderStatus status;
    private int num;

    public Order(Date createTime, Date updateTime, Date deleteTime, int userId, int productId, BigDecimal price, OrderStatus status,int num) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteTime = deleteTime;
        this.userId = userId;
        this.productId = productId;
        this.price = price;
        this.status = status;
        this.num=num;
    }
}
