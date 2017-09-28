package com.spring.service.impl;

import com.spring.common.model.model.RedisKey;
import com.spring.common.model.util.tools.BeanToMapUtil;
import com.spring.domain.Product;
import com.spring.domain.request.ProductUpdateRequest;
import com.spring.persistence.ProductMapper;
import com.spring.service.ProductService;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

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
    public Product getProductById(Integer productId) throws IllegalAccessException, IntrospectionException, InvocationTargetException, InstantiationException {
        String key= RedisKey.producth+productId;
            Map productM=redisTemplate.opsForHash().entries(key);
            if(Objects.isNull(productM) || productM.isEmpty()) {
                logger.info("查询数据库");
                Product product=productMapper.getProductById(productId);
                if(Objects.nonNull(product)) {
                    redisTemplate.opsForHash().putAll(key, BeanToMapUtil.convertBean(product));
                }
                return product;
            }else{
            return (Product) BeanToMapUtil.convertMap(Product.class,productM);
        }
    }

    @Override
    public int updateProduct(ProductUpdateRequest productUpdateRequest) {
        int flag=productMapper.updateProduct(productUpdateRequest.getProductId(),productUpdateRequest.getProductName(),productUpdateRequest.getStock(),productUpdateRequest.getPrice());
        if(flag==1){
            String key=RedisKey.producth+productUpdateRequest.getProductId();
            if(redisTemplate.opsForHash().hasKey(key,"productId")) {
                redisTemplate.opsForHash().put(key,"stock",productUpdateRequest.getStock());
                redisTemplate.opsForHash().put(key,"name",productUpdateRequest.getProductName());
                redisTemplate.opsForHash().put(key,"price",productUpdateRequest.getPrice());
                redisTemplate.opsForHash().put(key,"updateTime",new Date());
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
            redisTemplate.delete(RedisKey.producth+productId);
        }
        return flag;
    }
}
