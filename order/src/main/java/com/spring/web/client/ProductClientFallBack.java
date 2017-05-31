package com.spring.web.client;

import com.spring.common.model.StatusCode;
import com.spring.domain.model.Product;
import com.spring.domain.response.ObjectDataResponse;
import feign.hystrix.FallbackFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
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
        };
    }
}
