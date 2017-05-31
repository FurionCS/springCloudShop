package com.spring.domain.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.common.model.response.RestfulResponse;
import lombok.*;

/**
 * @author Zhao Junjian
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectDataResponse<T> extends RestfulResponse {

    private static final long serialVersionUID = 1562741318988223256L;

    private T data;

    public ObjectDataResponse(T data) {
        this.data = data;
    }
}
