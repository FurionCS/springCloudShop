package com.spring.domain.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ErnestCheng on 2017/8/16.
 */
@Data
public class UserUpdateRequest {
    @NotNull
    private Integer id;
    private String idCard;
}
