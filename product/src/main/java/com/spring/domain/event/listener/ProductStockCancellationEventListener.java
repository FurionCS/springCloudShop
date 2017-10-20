package com.spring.domain.event.listener;

import com.google.common.base.Preconditions;
import com.spring.domain.ProductStockTcc;
import com.spring.domain.event.ProductStockCancellationEvent;
import com.spring.service.ProductStockTccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:产品取消消息监听
 * @Author : Mr.Cheng
 * @Date:2017/6/4
 */
@Component
public class ProductStockCancellationEventListener implements ApplicationListener<ProductStockCancellationEvent>{

    private final ProductStockTccService tccService;

    @Autowired
    public ProductStockCancellationEventListener(ProductStockTccService tccService) {
        this.tccService = tccService;
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void onApplicationEvent(ProductStockCancellationEvent productStockCancellationEvent) {
        Preconditions.checkNotNull(productStockCancellationEvent);
        ProductStockTcc productStockTcc=(ProductStockTcc)productStockCancellationEvent.getSource();
        tccService.cancelReservation(productStockTcc);
    }
}
