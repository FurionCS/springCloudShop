package com.spring.web;

import com.spring.domain.model.Participant;
import com.spring.domain.model.UserBalanceTcc;
import com.spring.domain.model.request.BalanceReservationRequest;
import com.spring.domain.model.response.ReservationResponse;
import com.spring.service.UserBalanceTccService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Description 用户余额
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@RestController
public class UserBalanceReservationController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private UserBalanceTccService userBalanceTccService;

    /**
     * 预留余额
     * @param balanceReservationRequest
     * @param result
     * @return
     */
    @ApiOperation(value = "预留余额", notes = "")
    @RequestMapping(value="/balances/reservation",method = RequestMethod.POST)
    public ReservationResponse reserve(@Valid @RequestBody BalanceReservationRequest balanceReservationRequest, BindingResult result){
       UserBalanceTcc balanceTcc= userBalanceTccService.trying(balanceReservationRequest.getUserId(),balanceReservationRequest.getAmount());
        Participant participant=new Participant("http://"+applicationName+"/balances/reservation/"+balanceTcc.getId(),balanceTcc.getExpireTime());
        return new ReservationResponse(participant);
    }
}
