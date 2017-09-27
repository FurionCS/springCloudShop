package com.spring.web;

import com.spring.common.model.response.ObjectDataResponse;
import com.spring.domain.model.Participant;
import com.spring.domain.model.UserBalanceTcc;
import com.spring.domain.model.request.BalanceReservationRequest;
import com.spring.service.UserBalanceTccService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;

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
    public ObjectDataResponse<Participant> reserve(@Valid @RequestBody BalanceReservationRequest balanceReservationRequest, BindingResult result) throws IntrospectionException, InstantiationException, IllegalAccessException, InvocationTargetException {
       UserBalanceTcc balanceTcc= userBalanceTccService.trying(balanceReservationRequest.getUserId(),balanceReservationRequest.getAmount());
        Participant participant=new Participant("http://"+applicationName+"/balances/reservation/"+balanceTcc.getId(),balanceTcc.getExpireTime());
        return new ObjectDataResponse<>(participant);
    }

    @ApiOperation(value="确认预留资源")
    @RequestMapping(value="/balances/reservation/{reservationId}",method = RequestMethod.PUT)
    public void confirm(@PathVariable Integer reservationId){
        userBalanceTccService.confirm(reservationId);
    }

    @ApiOperation(value="取消预留资源")
    @RequestMapping(value="/balances/reservation/{reservationId}",method = RequestMethod.DELETE)
    public void cancel(@PathVariable Integer reservationId){
        userBalanceTccService.cancelReservation(reservationId);
    }

}
