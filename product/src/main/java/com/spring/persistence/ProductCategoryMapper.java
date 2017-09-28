package com.spring.persistence;

import com.spring.domain.ProductCategory;
import com.spring.domain.type.ProductCategoryStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ErnestCheng on 2017/9/27.
 */
@Repository
public interface ProductCategoryMapper {
    /**
     * 获得某种状态，排序权重大于等于多少的产品分类列表
     * @param status
     * @param sortOrderStart
     * @param sortOrderEnd
     * @param pageSize
     * @param startIndex
     * @return
     */
    List<ProductCategory> listProductCategory(@Param("status") ProductCategoryStatus status,@Param("sortOrderStart") Integer sortOrderStart,@Param("sortOrderEnd") Integer sortOrderEnd,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);

    /**
     * 获得某种状态，排序权重大于等于多少的产品分类数量
     * @param status
     * @param sortOrderStart
     * @param sortOrderEnd
     * @return
     */
    Integer getProductCategoryCount(@Param("status") ProductCategoryStatus status,@Param("sortOrderStart") Integer sortOrderStart,@Param("sortOrderEnd") Integer sortOrderEnd);

    /**
     * 更新产品分类信息
     * @param id
     * @param name
     * @param sortOrder
     * @param status
     * @return
     */
    Integer updateProductCategory(@Param("id") Integer id,@Param("name") String name,@Param("sortOrder") Integer sortOrder,@Param("status") ProductCategoryStatus status);

    /**
     * 获得产品分类通过id
     * @param id
     * @return
     */
    ProductCategory getProductCategoryById(@Param("id") Integer id);

    /**
     * 新增产品分类
     * @param productCategory
     * @return
     */
    Integer addProductCategory(ProductCategory productCategory);
}
