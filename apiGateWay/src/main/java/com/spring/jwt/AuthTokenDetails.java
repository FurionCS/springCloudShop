package com.spring.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * session信息模型
 *
 * @author yangfan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenDetails implements java.io.Serializable {

    private Long id;// 用户ID
    private String username;// 用户登录名
    private String ip;// 用户IP

    private List<String> roleNames;
    private Date expirationDate;

}
