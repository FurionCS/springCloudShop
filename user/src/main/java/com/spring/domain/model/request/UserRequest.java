package com.spring.domain.model.request;

import com.spring.common.model.request.RestfulRequest;
import lombok.Data;

/**
 * @Description 用户请求
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Data
public class UserRequest extends RestfulRequest {

    private String userName;

    private String password;
}
