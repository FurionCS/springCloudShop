package com.spring.common.model.exception;

import com.spring.common.model.StatusCode;
import com.spring.common.model.model.ErrorInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description 全局异常
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Getter
@Setter
public class GlobalException extends  RuntimeException {

    private ErrorInfo errorInfo;

    public GlobalException(String message, StatusCode code){
        super(message);
        this.errorInfo.setCode(code);
        this.errorInfo.setMessage(message);
    }

}
