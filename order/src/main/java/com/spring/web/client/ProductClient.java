package com.spring.web.client;

import com.spring.common.model.response.ObjectDataResponse;
import com.spring.domain.Participant;
import com.spring.domain.Product;
import com.spring.domain.request.StockReservationRequest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * @Description 产品接口
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@FeignClient(name="product",fallbackFactory = ProductClientFallBack.class)
public interface ProductClient {

    @RequestMapping(value = "getProductById",method = RequestMethod.GET)
    ObjectDataResponse<Product> getProductById(@RequestParam("productId") Integer productId);

    @RequestMapping(value="/productStock/reservation",method = RequestMethod.POST)
    ObjectDataResponse<Participant> reserve(@RequestBody StockReservationRequest stockReservationRequest);

}
