package com.spring.web;

import com.spring.domain.model.Participant;
import com.spring.domain.model.ProductStockTcc;
import com.spring.domain.request.StockReservationRequest;
import com.spring.domain.response.ReservationResponse;
import com.spring.service.ProductStockTccService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @Description 产品库存tcc
 * @Author ErnestCheng
 * @Date 2017/6/1.
 */
@RestController
public class ProductStockTccController {

    Logger logger= Logger.getLogger(ProductStockTccController.class);

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
    public ReservationResponse reserve(@Valid @RequestBody StockReservationRequest stockReservationRequest, BindingResult result){
        System.out.println(stockReservationRequest.getProductId());
        ProductStockTcc productStockTcc=productStockTccService.trying(stockReservationRequest.getProductId(),stockReservationRequest.getNum());
        Participant participant=new Participant();
        participant.setExpireTime(productStockTcc.getExpireTime());
        participant.setUri("http://"+applicationName+"/productStock/reservation/"+productStockTcc.getId());
        return new ReservationResponse(participant);
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
