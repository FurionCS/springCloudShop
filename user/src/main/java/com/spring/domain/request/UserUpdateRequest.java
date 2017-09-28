package com.spring.domain.request;

import lombok.Data;
import javax.validation.constraints.NotNull;


/**
 * Created by ErnestCheng on 2017/8/16.
 */
@Data
public class UserUpdateRequest {
    @NotNull
    private Integer id;
    private String idCard;
}
