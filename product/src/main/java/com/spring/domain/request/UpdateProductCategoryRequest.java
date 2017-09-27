package com.spring.domain.request;

import com.spring.domain.type.ProductCategoryStatus;
import lombok.Data;
import lombok.NonNull;

/**
 * Created by Mr.Cheng on 2017/9/27.
 */
@Data
public class UpdateProductCategoryRequest {
    @NonNull
    private int id;
    private String name;
    private Integer sortOrder;
    private ProductCategoryStatus status;
}
