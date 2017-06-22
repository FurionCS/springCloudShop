package com.spring.domain.model.response;

import com.spring.common.model.response.RestfulResponse;
import lombok.Data;

/**
 * @Description 登入响应
 * @Author ErnestCheng
 * @Date 2017/6/22.
 */
@Data
public class LoginResponse  extends RestfulResponse {
    /**
     * token
     */
    private String token;

}
