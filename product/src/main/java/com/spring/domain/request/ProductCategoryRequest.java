package com.spring.domain.request;

import com.spring.common.model.request.PageRequest;
import com.spring.domain.type.ProductCategoryStatus;
import lombok.Data;
import lombok.NonNull;

/**
 * Created by ErnestCheng on 2017/9/27.
 */
@Data
public class ProductCategoryRequest extends PageRequest{
    /**
     * 分类类型
     */
    @NonNull
    private ProductCategoryStatus productCategoryStatus;
    /**
     * 排序权重start
     */
    @NonNull
    private Integer sortOrderStart;
    /**
     * 排序权重end
     */
    @NonNull
    private Integer sortOrderEnd;
}
