package com.spring.domain.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.spring.common.model.request.RestfulRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Zhao Junjian
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BalanceReservationRequest extends RestfulRequest {

    private static final long serialVersionUID = -7315046960598963993L;

    @NotNull
    @Min(1)
    @ApiModelProperty(value = "用户ID", example = "1", required = true)
    private Integer userId;

    @NotNull
    @Min(1)
    @Max(100000000L)
    @ApiModelProperty(value = "预留金额", example = "100", required = true)
    private BigDecimal amount;

}
