package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.common.model.model.RedisKey;
import com.spring.domain.model.ProductCategory;
import com.spring.domain.type.ProductCategoryStatus;
import com.spring.persistence.ProductCategoryMapper;
import com.spring.service.ProductCategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by ErnestCheng on 2017/9/27.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private static final Logger LOGGER = Logger.getLogger(ProductCategoryServiceImpl.class);
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<ProductCategory> findProductCategory(ProductCategoryStatus status, Integer sortOrderStart,Integer sortOrderEnd, Integer pageIndex, Integer pageSize) {
        Preconditions.checkNotNull(sortOrderStart);
        Preconditions.checkNotNull(sortOrderEnd);
        int startIndex = pageIndex == -1 ? 0 : (pageIndex - 1) * pageSize;
        Set productCategoryList = redisTemplate.opsForZSet().rangeByScore(RedisKey.productCategory + status.getStatus(), sortOrderStart,sortOrderEnd  , startIndex, pageSize<=0? -1:pageSize);
        if (Objects.isNull(productCategoryList) || productCategoryList.isEmpty()){
            List<ProductCategory> productCategories=productCategoryMapper.listProductCategory(status,sortOrderStart,sortOrderEnd,startIndex,pageSize);
            if(Objects.nonNull(productCategories)&& !productCategories.isEmpty()){
                Set<ZSetOperations.TypedTuple<ProductCategory>> tuples=new HashSet<>();
                productCategories.forEach(productCategory -> {
                    tuples.add(new DefaultTypedTuple<ProductCategory>(productCategory,productCategory.getSortOrder().doubleValue()));
                });
                redisTemplate.opsForZSet().add(RedisKey.productCategory+status.getStatus(),tuples);
            }
            return productCategories;
        }
        return new ArrayList<>(productCategoryList);
    }
}
