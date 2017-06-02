package com.spring.common.model.model;

import com.spring.common.model.StatusCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description 错误类
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Getter
@Setter
public class ErrorInfo<T> {

    private StatusCode code;
    private String message;
    private String url;
    private T data;
}
