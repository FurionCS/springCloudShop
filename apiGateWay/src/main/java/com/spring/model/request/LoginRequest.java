package com.spring.model.request;

import lombok.Data;

/**
 * @Description 登入
 * @Author ErnestCheng
 * @Date 2017/6/26.
 */
@Data
public class LoginRequest {
    /**
     * 账号
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
}
