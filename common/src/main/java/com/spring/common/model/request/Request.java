package com.spring.common.model.request;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Setter;

/**
 * @Description 请求
 * @Author ErnestCheng
 * @Date 2017/5/26.
 */
@Setter
public class Request {
    // api请求类型
    protected String action;
    protected Integer uid;
    // 令牌
    protected String token;
}
