package com.spring.domain;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:用户对象
 * @Author : Mr.Cheng
 * @Date:2017/5/26
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable{
    private Integer id;
    private String userName;
    private String password;
    private String idCard;
    private BigDecimal balance;
    private Date createTime;
    private Date leastTime;
}
