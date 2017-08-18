package com.spring.service.impl;

import com.spring.common.model.model.RedisKey;
import com.spring.domain.model.Product;
import com.spring.domain.request.ProductUpdateRequest;
import com.spring.persistence.ProductMapper;
import com.spring.service.ProductService;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description 产品service实现
 * @Author ErnestCheng
 * @Date 2017/5/27.
 */
@Service
public class ProductServiceImpl implements ProductService {

    Logger logger= Logger.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);
    }

    @Override
    public Product getProductById(Integer productId) {
        String key= RedisKey.product+productId;
            Product product=(Product)redisTemplate.opsForValue().get(key);
            if(product==null) {
                logger.info("查询数据库");
                Product product2=productMapper.getProductById(productId);
                if(product2!=null) {
                    redisTemplate.opsForValue().set(key, product2);
                }
                return product2;
            }else{
            return product;
        }
    }

    @Override
    public int updateProduct(ProductUpdateRequest productUpdateRequest) {
        int flag=productMapper.updateProduct(productUpdateRequest.getProductId(),productUpdateRequest.getProductName(),productUpdateRequest.getStock(),productUpdateRequest.getPrice());
        if(flag==1){
            Product product=(Product)redisTemplate.opsForValue().get(RedisKey.product+productUpdateRequest.getProductId());
            if(product!=null) {
                product.setStock(productUpdateRequest.getStock());
                product.setName(productUpdateRequest.getProductName());
                product.setPrice(productUpdateRequest.getPrice());
                product.setUpdateTime(new Date());
                redisTemplate.opsForValue().set(RedisKey.product + productUpdateRequest.getProductId(), product);
            }
        }
        return flag;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteProductByProductId(Integer productId) {
        // 1：先删除数据库中的产品
        int flag=productMapper.deleteProductByProductId(productId);
        // 2: 删除redis中数据
        if(flag==1){
            redisTemplate.delete(RedisKey.product+productId);
        }
        return flag;
    }
}
