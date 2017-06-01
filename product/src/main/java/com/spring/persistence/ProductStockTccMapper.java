package com.spring.persistence;

import com.spring.domain.model.ProductStockTcc;
import org.springframework.stereotype.Repository;

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
}
