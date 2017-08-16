package com.spring.domain.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Description:产品更新requset
 * @author Mr.Cheng
 * @Date 2017-08-16
 * @version  1.0
 */
@Data
public class ProductUpdateRequest {

    /**
     * 产品id
     */
    @NotNull
    @Min(1)
    private Integer productId;
    /**
     * 产品名称
     */
    @NotNull
    private String productName;
    /**
     * 库存
     */
    @NotNull
    @Min(0)
    private Integer stock;
    /**
     * 价格
     */
    @NotNull
    @Min(0)
    private BigDecimal price;
}
