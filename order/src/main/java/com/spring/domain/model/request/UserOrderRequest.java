package com.spring.domain.model.request;

import com.spring.common.model.request.RestfulRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Description:用户订单请求
 * @Author : Mr.Cheng
 * @Date:2017/5/26
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserOrderRequest extends RestfulRequest {

    @NotNull
    @Min(1)
    @ApiModelProperty(value="用户id",example = "1",required = true)
    private Integer userId;
}
