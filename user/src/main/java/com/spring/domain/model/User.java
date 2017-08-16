package com.spring.domain.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
public class User implements Serializable{

    private Integer id;
    @NotNull
    @Length(min=6,max=50,message = "用户名长度必须在6-50位之间")
    private String userName;
    @NotNull
    @Length(min=6,max=16,message = "密码长度必须在6-16位之间")
    private String password;
    private String idCard;
    @Min(0)
    private BigDecimal balance;
    private Date createTime;
    private Date leastTime;
}
