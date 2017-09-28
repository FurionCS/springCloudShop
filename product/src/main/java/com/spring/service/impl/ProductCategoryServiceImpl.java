package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
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
 * 产品分类Service
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
        Preconditions.checkNotNull(sortOrderStart,"排序权重sortOrderStart不能为空");
        Preconditions.checkNotNull(sortOrderEnd,"排序权重sortOrderStart不能为空");
        int startIndex = pageIndex == -1 ? 0 : (pageIndex - 1) * pageSize;
        //查看redis中是否有
        Set productCategoryList = redisTemplate.opsForZSet().rangeByScore(RedisKey.productCategory + status.getStatus(), sortOrderStart,sortOrderEnd  , startIndex, pageSize<=0? -1:pageSize);
        //redis中没有
        if (Objects.isNull(productCategoryList) || productCategoryList.isEmpty()){
            //查询数据库
            List<ProductCategory> productCategories=productCategoryMapper.listProductCategory(status,sortOrderStart,sortOrderEnd,startIndex,pageSize);
            if(Objects.nonNull(productCategories)&& !productCategories.isEmpty()){
                //组装数据
                Set<ZSetOperations.TypedTuple<ProductCategory>> tuples=new HashSet<>();
                productCategories.forEach(productCategory -> {
                    tuples.add(new DefaultTypedTuple<ProductCategory>(productCategory,productCategory.getSortOrder().doubleValue()));
                });
                //存入redis
                redisTemplate.opsForZSet().add(RedisKey.productCategory+status.getStatus(),tuples);
            }
            return productCategories;
        }
        //redis中有直接返回
        return new ArrayList<>(productCategoryList);
    }

    @Override
    public Integer getProductCategoryCount(ProductCategoryStatus status, Integer sortOrderStart, Integer sortOrderEnd) {
        Preconditions.checkNotNull(sortOrderStart,"排序权重sortOrderStart不能为空");
        Preconditions.checkNotNull(sortOrderEnd,"排序权重sortOrderStart不能为空");
        Long count=redisTemplate.opsForZSet().count(RedisKey.productCategory+status.getStatus(),sortOrderStart,sortOrderEnd);
        if(Objects.isNull(count) || count==0){
            //这里大部分情况不会进入
            return productCategoryMapper.getProductCategoryCount(status,sortOrderStart,sortOrderEnd);
        }
        return count.intValue();
    }

    @Override
    public Integer updateProductCategory(Integer id, String name, Integer sortOrder, ProductCategoryStatus status) {
        Preconditions.checkArgument(id!=null && id>=0,"产品分类id参数不正确");
        if(Strings.isNullOrEmpty(name) && Objects.isNull(sortOrder) && Objects.isNull(status)){
            return 0;
        }
        //先获得旧类型，更新数据库，获得新类型
        ProductCategory productCategory=productCategoryMapper.getProductCategoryById(id);
        Integer count=productCategoryMapper.updateProductCategory(id, name, sortOrder, status);
        redisTemplate.opsForZSet().remove(RedisKey.productCategory+productCategory.getStatus().getStatus(),productCategory);
        ProductCategory productCategoryNew=productCategoryMapper.getProductCategoryById(id);
        if(Objects.equals(productCategory.getStatus(),status)){
            redisTemplate.opsForZSet().add(RedisKey.productCategory+productCategory.getStatus().getStatus(),productCategoryNew,productCategoryNew.getSortOrder());
        }else{
            redisTemplate.opsForZSet().add(RedisKey.productCategory+status.getStatus(),productCategoryNew,productCategoryNew.getSortOrder());
        }
        return count;
    }
}
