package com.spring.web.client;

import com.spring.domain.model.Product;
import com.spring.domain.request.StockReservationRequest;
import com.spring.domain.response.ObjectDataResponse;
import com.spring.domain.response.ReservationResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
    ReservationResponse reserve(@RequestBody StockReservationRequest stockReservationRequest);

}
