package com.spring.service;

import com.spring.domain.model.ProductStockTcc;

/**
 * @Description 产品库存TccService
 * @Author ErnestCheng
 * @Date 2017/6/1.
 */
public interface ProductStockTccService{
    /**
     * 预留资源
     * @param productId
     * @param num
     * @return
     */
    ProductStockTcc trying(Integer productId,Integer num);

    /**
     * 确认预留资源
     * @param reservationId
     */
    void confirmReservation(Integer reservationId);

    /**
     * 取消预留资源
     * @param id
     */
    void cancelReservation(Integer id);

    /**
     * 取消预留资源
     * @param res
     * @return
     */
    void cancelReservation(ProductStockTcc res);
}
