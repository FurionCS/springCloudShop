package com.spring.service;

import com.spring.domain.model.ProductCategory;
import com.spring.domain.type.ProductCategoryStatus;

import java.util.List;

/**
 * Created by ErnestCheng on 2017/9/27.
 */
public interface ProductCategoryService {
    /**
     * 获得某种状态，排序权重大于等于多少的产品分离分页列表
     * @param status
     * @param sortOrderStart
     * @param sortOrderEnd
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<ProductCategory> findProductCategory(ProductCategoryStatus status,Integer sortOrderStart,Integer sortOrderEnd,Integer pageIndex,Integer pageSize);

    /**
     * 获得某种状态，排序权重大于等于多少的产品分类数量
      * @param status
     * @param sortOrderStart
     * @param sortOrderEnd
     * @return
     */
    Integer getProductCategoryCount(ProductCategoryStatus status,Integer sortOrderStart,Integer sortOrderEnd);

    /**
     * 更改产品分类
     * @param id
     * @param name
     * @param sortOrder
     * @param status
     * @return
     */
    Integer updateProductCategory(Integer id,String name,Integer sortOrder,ProductCategoryStatus status);
}
