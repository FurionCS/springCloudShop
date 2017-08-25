package com.spring.domain.model.response;


import com.spring.common.model.response.ObjectDataResponse;
import lombok.Data;

/**
 * @Description 登入响应
 * @Author ErnestCheng
 * @Date 2017/6/22.
 */
@Data
public class LoginResponse  extends ObjectDataResponse {
    /**
     * token
     */
    private String token;

}
