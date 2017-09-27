package com.spring.common.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.common.model.StatusCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author Zhao Junjian
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectDataResponse<T> extends RestfulResponse {
    private T data;
    public ObjectDataResponse(StatusCode code, String message){
        super(code, message);
    }
    public ObjectDataResponse(T data) {
        if(Objects.nonNull(data)) {
            this.data = data;
        }else{
            this.message="数据不存在";
            this.code=StatusCode.Data_Not_Exist;
        }
    }
    public ObjectDataResponse(String active, StatusCode code, String message){
        super(active,code, message);
    }

    public ObjectDataResponse(Throwable e){
        super();
        this.message=e.getMessage();
        this.code=StatusCode.Fail_Code;
    }
}
