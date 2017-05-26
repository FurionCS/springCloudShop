package com.spring.domain.model;

import lombok.*;

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
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String idCard;
    private Double balance;
    private Date createTime;
    private Date leastTime;
}
