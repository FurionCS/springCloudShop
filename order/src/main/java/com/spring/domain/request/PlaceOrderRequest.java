package com.spring.domain.request;

import com.spring.common.model.request.RestfulRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;

/**
 * @Description 生成预订单请求
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PlaceOrderRequest extends RestfulRequest {

    @NonNull
    @Min(1)
    @ApiModelProperty(value="产品id",required = true,example = "1")
    private Integer productId;

    @NonNull
    @Min(1)
    @ApiModelProperty(value="用户id",required = true,example = "1")
    private Integer userId;

    @NonNull
    @Min(1)
    @ApiModelProperty(value="订单产品数量",required = true,example = "1")
    private Integer num;

}
