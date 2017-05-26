package com.spring.common.model.request;

import lombok.*;

/**
 * @author Zhao Junjian
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RestfulRequest implements Request {
    private static final long serialVersionUID = -2363877433041183308L;
    // api请求类型
    protected String action;
    // 令牌
    protected String token;
}
