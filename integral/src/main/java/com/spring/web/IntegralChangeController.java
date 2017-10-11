package com.spring.web;

import com.spring.common.model.response.ObjectDataResponse;
import com.spring.domain.model.IntegralChange;
import com.spring.service.IntegralChangeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
@RestController
@RequestMapping("/integral/change")
public class IntegralChangeController {

    @Autowired
    private IntegralChangeService integralChangeService;

    @PostMapping("/add")
    @ApiOperation("新增积分规则")
    public ObjectDataResponse addIntegralChange(@RequestBody IntegralChange integralChange){
        integralChangeService.addIntegralChange(integralChange);
        return new ObjectDataResponse();
    }

}
