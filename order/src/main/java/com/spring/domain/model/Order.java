package com.spring.domain.model;

import lombok.*;

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
    private Double price;
    private int status;
}
