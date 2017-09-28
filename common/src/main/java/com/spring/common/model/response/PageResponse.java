package com.spring.common.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.common.model.StatusCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Created by Mr.Cheng on 2017/9/23.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> extends RestfulResponse {
    /**
     * 总条数
     */
    private Integer totalCount;
    /**
     * 数据
     */
    private T data;

    public PageResponse(T date,Integer totalCount){
        if(Objects.nonNull(date)){
            this.data=date;
            this.totalCount=totalCount;
        }else{
            this.message="数据不存在";
            this.code=StatusCode.Data_Not_Exist;
        }
    }

    public PageResponse(Throwable e){
        super();
        this.message=e.getMessage();
        this.code= StatusCode.Fail_Code;
    }
}
