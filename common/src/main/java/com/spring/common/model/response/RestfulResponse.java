package com.spring.common.model.response;

import com.spring.common.model.StatusCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author  Mr.Cheng
 */
@Getter
@Setter
public class RestfulResponse implements Response {
    private static final long serialVersionUID = -7443304902819898146L;
    // api请求类型
    protected String action;
    // 状态码
    protected StatusCode code=StatusCode.Success;
    // 状态信息
    protected String message="操作成功";
    //参数
//    @JsonIgnore
    private Object[] args;


}
