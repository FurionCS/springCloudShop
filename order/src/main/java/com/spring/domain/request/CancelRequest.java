package com.spring.domain.request;

import com.spring.common.model.request.RestfulRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Description 支付订单
 * @Author ErnestCheng
 * @Date 2017/6/2.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CancelRequest extends RestfulRequest {

    @Min(1)
    @NotNull
    @ApiModelProperty(value="订单id",example = "1",required = true)
    private Integer orderId;
}
