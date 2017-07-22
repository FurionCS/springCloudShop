package com.spring.domain.model;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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
    @NotNull
    private String userName;
    private String password;
    private String idCard;
    @Min(1000)
    private Double balance;
    private Date createTime;
    private Date leastTime;
}
