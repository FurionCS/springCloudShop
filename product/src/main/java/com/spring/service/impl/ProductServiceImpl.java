package com.spring.service.impl;

import com.spring.domain.model.Product;
import com.spring.persistence.ProductMapper;
import com.spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 产品service实现
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);
    }
}
