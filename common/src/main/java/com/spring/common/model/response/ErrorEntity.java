package com.spring.common.model.response;


import com.spring.common.model.StatusCode;
import lombok.Getter;
import lombok.Setter;
/**
 * @author Zhao Junjian
 */
@Getter
@Setter
public class ErrorEntity implements Response {
    // api请求类型
    protected String action;
    // 状态码
    protected StatusCode code;
    // 状态信息
    protected String message;
    //参数
//    @JsonIgnore
    private Object[] args;
}
