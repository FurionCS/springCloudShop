package com.spring.model.response;

import com.spring.model.StatusCode;
import lombok.Data;

/**
 * @author  Mr.Cheng
 */
@Data
public class RestfulResponse implements Response {
    private static final long serialVersionUID = -7443304902819898146L;
    // api请求类型
    protected String action;
    // 状态码
    protected StatusCode code= StatusCode.Success;
    // 状态信息
    protected String message="操作成功";
    //参数
//    @JsonIgnore
    private Object[] args;

    public RestfulResponse() {
    }

    public RestfulResponse(String action, StatusCode code, String message) {
        this.action = action;
        this.code = code;
        this.message = message;
    }

    public RestfulResponse(String action, StatusCode code, String message, Object[] args) {
        this.action = action;
        this.code = code;
        this.message = message;
        this.args = args;
    }
}
