package com.spring.web;

import com.spring.domain.model.Participant;
import com.spring.domain.model.ProductStockTcc;
import com.spring.domain.request.StockReservationRequest;
import com.spring.domain.response.ReservationResponse;
import com.spring.service.ProductStockTccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Description 产品库存tcc
 * @Author ErnestCheng
 * @Date 2017/6/1.
 */
@RestController
public class ProductStockTccController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private ProductStockTccService productStockTccService;

    @RequestMapping(value="/productStock/reservation",method = RequestMethod.POST)
    public ReservationResponse reserve(@Valid @RequestBody StockReservationRequest stockReservationRequest, BindingResult result){
        System.out.println(stockReservationRequest.getProductId());
        ProductStockTcc productStockTcc=productStockTccService.trying(stockReservationRequest.getProductId(),stockReservationRequest.getNum());
        Participant participant=new Participant();
        participant.setExpireTime(productStockTcc.getExpireTime());
        participant.setUri("http://"+applicationName+"/productStock/reservation/"+productStockTcc.getId());
        return new ReservationResponse(participant);
    }
}
