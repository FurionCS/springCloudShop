package com.spring.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description 用户响应
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserResponse<T> extends RestfulResponse{

    private T data;

    public UserResponse(T data) {
        this.data = data;
    }
}
