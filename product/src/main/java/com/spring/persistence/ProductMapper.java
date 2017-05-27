package com.spring.persistence;

import com.spring.domain.model.Product;
import org.springframework.stereotype.Repository;

/**
 * @Description 产品Mapper
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Repository
public interface ProductMapper {
    /**
     * 添加产品
     * @param product
     */
    void addProduct(Product product);
}
