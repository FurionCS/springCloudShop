package com.spring.model;





import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

/**
 * @Description 错误类
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ErrorInfo<T> {

    private StatusCode code;
    private String message;
    private String url;
    private T data;
    private OffsetDateTime createTime;

    public ErrorInfo(StatusCode code, String message, String url,T data) {
        this.code=code;
        this.message=message;
        this.url=url;
        this.data=data;
        this.createTime=OffsetDateTime.now();
    }
    public ErrorInfo(StatusCode code, String message, String url, T data, OffsetDateTime createTime) {
        this.code = code;
        this.message = message;
        this.url = url;
        this.data = data;
        this.createTime = createTime;
    }
}
