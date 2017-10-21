package com.spring.web;

import com.spring.common.model.response.ObjectDataResponse;
import com.spring.domain.Participant;
import com.spring.domain.ProductStockTcc;
import com.spring.domain.request.StockReservationRequest;
import com.spring.service.ProductStockTccService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;


/**
 * @Description 产品库存tcc
 * @Author ErnestCheng
 * @Date 2017/6/1.
 */
@RestController
public class ProductStockTccController {

    private static final  Logger logger= Logger.getLogger(ProductStockTccController.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private ProductStockTccService productStockTccService;

    /**
     * 预留资源
     * @param stockReservationRequest
     * @param result
     * @return
     */
    @ApiOperation(value="预留资源")
    @RequestMapping(value="/productStock/reservation",method = RequestMethod.POST)
    public ObjectDataResponse reserve(@Valid @RequestBody StockReservationRequest stockReservationRequest, BindingResult result) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ProductStockTcc productStockTcc=productStockTccService.trying(stockReservationRequest.getProductId(),stockReservationRequest.getNum());
        Participant participant=Participant.builder()
                .expireTime(productStockTcc.getExpireTime())
                .uri("http://"+applicationName+"/productStock/reservation/"+productStockTcc.getId()).build();
        return new ObjectDataResponse(participant);
    }

    @ApiOperation("确认预留资源")
    @RequestMapping(value="/productStock/reservation/{reservationId}",method = RequestMethod.PUT)
    public void confirm(@PathVariable Integer reservationId){
        productStockTccService.confirmReservation(reservationId);
    }

    @ApiOperation("取消预留资源")
    @RequestMapping(value="/productStock/reservation/{reservationId}",method = RequestMethod.DELETE)
    public void cancel(@PathVariable Integer reservationId){
        logger.info("取消预留资源");
        productStockTccService.cancelReservation(reservationId);
    }
}
