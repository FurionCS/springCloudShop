package com.spring.persistence;

import com.spring.domain.model.ProductCategory;
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
     * 获得某种状态，排序权重大于等于多少的产品分离列表
     * @param status
     * @param sortOrderStart
     * @param sortOrderEnd
     * @param pageSize
     * @param startIndex
     * @return
     */
    List<ProductCategory> listProductCategory(@Param("status") ProductCategoryStatus status,@Param("sortOrderStart") Integer sortOrderStart,@Param("sortOrderEnd") Integer sortOrderEnd,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);


}
