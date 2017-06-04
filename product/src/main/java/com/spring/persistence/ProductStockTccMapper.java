package com.spring.persistence;

import com.spring.domain.model.ProductStockTcc;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @Description 产品库存tccMapper
 * @Author ErnestCheng
 * @Date 2017/6/1.
 */
@Repository
public interface ProductStockTccMapper {
    /**
     * 添加产品库存
     * @param productStockTcc
     * @return
     */
    int addProductStockTcc(ProductStockTcc productStockTcc);

    /**
     * 通过id获得预留资源信息
     * @param id
     * @return
     */
    ProductStockTcc getProductStockTccById(@Param("id") Integer id);

    /**
     * 更新预留资源状态
     * @param id
     * @return
     */
    int updateProductStockTccStatus(@Param("id") Integer id);

    /**
     * 搜索过期资源
     * @param limitation
     * @return
     */
    Set<ProductStockTcc> selectExpireReservation(@Param("limitation") int limitation);

    /**
     * 删除库存预留资源
     * @param id
     * @return
     */
    int deleteProductStockTcc(Integer id);
}
