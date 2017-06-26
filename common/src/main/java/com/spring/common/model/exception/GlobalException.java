package com.spring.common.model.exception;

import com.spring.common.model.StatusCode;
import com.spring.common.model.model.ErrorInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description 全局运行异常
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Getter
@Setter
@NoArgsConstructor
public class GlobalException extends  RuntimeException {

    private ErrorInfo errorInfo=new ErrorInfo();

    public GlobalException(String message, StatusCode code){
        super(message);
        this.errorInfo.setCode(code);
        this.errorInfo.setMessage(message);
    }

    public GlobalException(String message){
        super(message);
        this.errorInfo.setCode(StatusCode.Fail_Code);
        this.errorInfo.setMessage(message);
    }

    public GlobalException(String message,Object t){
        super(message);
        this.errorInfo.setMessage(message);
        this.errorInfo.setData(t);
    }


}
