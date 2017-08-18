package com.spring.domain.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by ErnestCheng on 2017/8/16.
 */
@Data
public class UserPasswordRequest {

    @NotNull
    @Min(1)
    private Integer userId;
    @NotNull
    @Length(min=6,max=16,message = "密码长度必须在6-16位之间")
    private String newPassword;

    @NotNull
    @Length(min=6,max=16,message="密码长度必须在6-16位之间")
    private String oldPassword;
}
