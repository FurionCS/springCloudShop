package com.spring.domain.request;

import com.spring.common.model.request.RestfulRequest;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Description 用户请求
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Data
public class UserRequest extends RestfulRequest {

    @NotNull
    @Length(min=6,max=50,message="用户姓名长度必须在6-50位之间")
    private String userName;

    @NotNull
    @Length(min=6,max=16,message = "密码长度必须在6-16位之间")
    private String password;
}
