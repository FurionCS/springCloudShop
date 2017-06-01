package com.spring.web.client;

import com.spring.common.model.StatusCode;
import com.spring.domain.model.Product;
import com.spring.domain.request.StockReservationRequest;
import com.spring.domain.response.ObjectDataResponse;
import com.spring.domain.response.ReservationResponse;
import feign.hystrix.FallbackFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description 产品接口回调
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@Component
public class ProductClientFallBack implements FallbackFactory<ProductClient> {

    Logger logger=Logger.getLogger(ProductClientFallBack.class);
    @Override
    public ProductClient create(Throwable throwable) {
        return new ProductClient() {
            @Override
            public ObjectDataResponse<Product> getProductById(@RequestParam Integer productId) {
                logger.error("调用产品接口失败："+throwable.getMessage());
                ObjectDataResponse objectDataResponse=new ObjectDataResponse();
                objectDataResponse.setCode(StatusCode.API_Fail);
                objectDataResponse.setMessage(throwable.getMessage());
                //TODO 记录到Mongodb
                return objectDataResponse;
            }
            @Override
            public ReservationResponse reserve(@RequestBody StockReservationRequest stockReservationRequest) {
                logger.error("调用产品预留库存接口失败："+throwable.getMessage());
                ReservationResponse reservationResponse=new ReservationResponse();
                reservationResponse.setCode(StatusCode.API_Fail);
                reservationResponse.setMessage(throwable.getMessage());
                //TODO 记录到Mongodb
                return reservationResponse;
            }
        };
    }
}
