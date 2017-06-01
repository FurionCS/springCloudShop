package com.spring.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.spring.common.model.request.RestfulRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
public class StockReservationRequest extends RestfulRequest {

    private static final long serialVersionUID = -5917718724808796934L;

    @NotNull
    @Min(1)
    @ApiModelProperty(value = "产品ID", example = "1", required = true)
    private Integer productId;

    @NotNull
    @Min(1)
    @ApiModelProperty(value="数量",example = "1",required = true)
    private Integer num;

}
