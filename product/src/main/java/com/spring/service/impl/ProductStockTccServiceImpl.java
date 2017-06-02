package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.common.model.StatusCode;
import com.spring.common.model.exception.GlobalException;
import com.spring.domain.model.Product;
import com.spring.domain.model.ProductStockTcc;
import com.spring.domain.type.TccStatus;
import com.spring.persistence.ProductMapper;
import com.spring.persistence.ProductStockTccMapper;
import com.spring.service.ProductStockTccService;
import com.sun.scenario.effect.Offset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @Description 产品库存TccService实现
 * @Author ErnestCheng
 * @Date 2017/6/1.
 */
@Service
public class ProductStockTccServiceImpl implements ProductStockTccService {

    private Long expireSeconds=15L;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductStockTccMapper productStockTccMapper;
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ProductStockTcc trying(Integer productId, Integer num) {
        //更新产品库存
        Preconditions.checkArgument(productId>0);
        Preconditions.checkArgument(num>0);
        Product product=productMapper.getProductById(productId);
        if(product==null){
            throw new GlobalException("产品不存在", StatusCode.Data_Not_Exist);
        }
        if(product.getStock()-num<0){
            throw new GlobalException("库存不够",StatusCode.Action_Fail);
        }
        int isLock=productMapper.updateProductStock(productId,num);
        if(isLock==0){
            throw new GlobalException("更新失败",StatusCode.Update_Fail);
        }

        // 插入产品预留资源
        ProductStockTcc productStockTcc=new ProductStockTcc();
        productStockTcc.setStock(num);
        productStockTcc.setStatus(TccStatus.TRY);
        productStockTcc.setProductId(productId);
        productStockTcc.setCreateTime(OffsetDateTime.now());
        productStockTcc.setExpireTime(OffsetDateTime.now().plusSeconds(expireSeconds));
        OffsetDateTime defaultDateTime=OffsetDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(8));
        productStockTcc.setUpdateTime(defaultDateTime);
        productStockTcc.setDeleteTime(defaultDateTime);
        productStockTccMapper.addProductStockTcc(productStockTcc);
        return productStockTcc;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void confirmReservation(Integer reservationId) {
        Preconditions.checkNotNull(reservationId);
        ProductStockTcc productStockTcc=productStockTccMapper.getProductStockTccById(reservationId);

        if(productStockTcc==null){
            throw new GlobalException("预留资源不存在",StatusCode.Data_Not_Exist);
        }
        System.out.println(productStockTcc);
        //如果是Try阶段则进行确认
        if(productStockTcc.getStatus()==TccStatus.TRY){
            int flag=productStockTccMapper.updateProductStockTccStatus(reservationId);
            if(flag==0){
                throw new GlobalException("resource " + reservationId + " has been cancelled",StatusCode.Update_Fail);
            }
        }
    }
}
