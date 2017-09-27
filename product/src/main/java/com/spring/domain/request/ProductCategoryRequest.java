package com.spring.domain.request;

import com.spring.common.model.request.PageRequest;
import com.spring.domain.type.ProductCategoryStatus;
import lombok.Data;

/**
 * Created by ErnestCheng on 2017/9/27.
 */
@Data
public class ProductCategoryRequest extends PageRequest{
    /**
     * 分类类型
     */
    private ProductCategoryStatus productCategoryStatus;
    /**
     * 排序权重start
     */
    private Integer sortOrderStart;
    /**
     * 排序权重end
     */
    private Integer sortOrderEnd;
}
