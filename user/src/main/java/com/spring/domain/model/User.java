package com.spring.domain.model;

import com.spring.domain.type.UserStatus;
import lombok.*;
import org.hibernate.validator.constraints.Length;

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
    /**
     * id
     */
    private Integer id;
    @NotNull
    @Length(min=3,max=50,message = "用户名长度必须在6-50位之间")
    private String userName;
    @NotNull
    @Length(min=6,max=50,message = "密码长度必须在6-16位之间")
    private String password;
    private String idCard;
    /**
     * 余额
     */
    @Min(0)
    private BigDecimal balance;
    /**
     * 用户状态
     */
    private UserStatus status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最新时间
     */
    private Date leastTime;

}
