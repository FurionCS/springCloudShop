package com.spring.common.model.response;


import com.spring.common.model.StatusCode;
import com.spring.common.model.request.RestStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 * @author Zhao Junjian
 */
@Data
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

    private Object details;



    public ErrorEntity(RestStatus statusCodes, Object details) {
        this.message = statusCodes.message();
        if (details != null) {
            this.details = details;
        }
    }
}
