package com.spring.web.client;

import com.spring.common.model.response.ObjectDataResponse;
import com.spring.domain.Participant;
import com.spring.domain.User;
import com.spring.domain.request.BalanceReservationRequest;

import org.springframework.cloud.netflix.feign.FeignClient;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * @Description 用户接口
 * @Author ErnestCheng
 * @Date 2017/5/31.
 */
@FeignClient(name="user",fallbackFactory = UserClientFallBack.class)
public interface UserClient {

    @RequestMapping(value="getUserById",method = RequestMethod.GET)
    ObjectDataResponse<User> getUserById(@RequestParam("userId") Integer userId);

    @RequestMapping(value="/balances/reservation" ,method = RequestMethod.POST)
    ObjectDataResponse<Participant> reserve(@RequestBody BalanceReservationRequest balanceReservationRequest);
}
