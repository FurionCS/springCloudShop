package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.common.model.StatusCode;
import com.spring.common.model.exception.GlobalException;
import com.spring.common.model.model.ErrorInfo;
import com.spring.common.model.model.RedisKey;
import com.spring.domain.model.Product;
import com.spring.domain.model.ProductStockTcc;
import com.spring.domain.type.TccStatus;
import com.spring.event.ProductStockCancellationEvent;
import com.spring.persistence.ProductMapper;
import com.spring.persistence.ProductStockTccMapper;
import com.spring.repository.ErrorRepository;
import com.spring.service.ProductService;
import com.spring.service.ProductStockTccService;
import com.sun.scenario.effect.Offset;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Set;

/**
 * @Description 产品库存TccService实现
 * @Author ErnestCheng
 * @Date 2017/6/1.
 */
@Service
public class ProductStockTccServiceImpl implements ProductStockTccService,ApplicationContextAware{

    private Long expireSeconds=15L;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductStockTccMapper productStockTccMapper;

    private ApplicationContext context;

    @Autowired
    private ErrorRepository errorRepository;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ProductStockTcc trying(Integer productId, Integer num) {
        //更新产品库存
        Preconditions.checkArgument(productId>0);
        Preconditions.checkArgument(num>0);
        Product product=productService.getProductById(productId);
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
        //更新reids
        product.setStock(product.getStock()-num);
        redisTemplate.opsForValue().getAndSet(RedisKey.product+":"+productId,product);

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

    /**
     * 资源回收策略为定时轮询数据库，暂时，存在资源竞争与重复计算嫌疑
     */
    @Scheduled(fixedRate = 1000)
    public void autoCancelTrying(){
        //获取过期资源
        Set<ProductStockTcc> reservations=productStockTccMapper.selectExpireReservation(100);
        reservations.forEach(productStockTcc -> {
            context.publishEvent(new ProductStockCancellationEvent(productStockTcc));
        });
    }

    @Override
    public void cancelReservation(Integer id) {
        Preconditions.checkNotNull(id);
        ProductStockTcc productStockTcc= productStockTccMapper.getProductStockTccById(id);
        this.cancelReservation(productStockTcc);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void cancelReservation(ProductStockTcc res) {
        Preconditions.checkNotNull(res);
        Preconditions.checkArgument(res.getId()>0);
        Preconditions.checkNotNull(res.getStatus());
        // 先删除预留信息
        if(res.getStatus()==TccStatus.TRY) {
            int flag = productStockTccMapper.deleteProductStockTcc(res.getId());
            //  恢复产品库存
            if (flag > 0) {
                int flag2 = productMapper.updateProductStock(res.getProductId(), -res.getStock());
                if (flag2 == 0) {
                    ErrorInfo errorInfo=new ErrorInfo<>(StatusCode.Update_Fail,"更新库存失败",null,res,OffsetDateTime.now());
                    errorRepository.insert(errorInfo);
                    throw new GlobalException("更新库存失败");
                }
                //设置redis
                Product product=(Product)redisTemplate.opsForValue().get(RedisKey.product+":"+res.getProductId());
                if(product!=null) {
                    product.setStock(product.getStock()+res.getStock());
                    redisTemplate.opsForValue().set(RedisKey.product + ":" + res.getProductId(),product);
                }
            }
        }
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }
}
