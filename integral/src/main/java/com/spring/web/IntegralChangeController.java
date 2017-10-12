package com.spring.web;

import com.google.common.base.Preconditions;
import com.spring.common.model.StatusCode;
import com.spring.common.model.response.ObjectDataResponse;
import com.spring.common.model.response.PageResponse;
import com.spring.domain.model.IntegralChange;
import com.spring.domain.type.IntegralChangeStatus;
import com.spring.service.IntegralChangeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

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
        Integer flag=integralChangeService.addIntegralChange(integralChange);
        if(flag>0){
            return  new ObjectDataResponse();
        }else {
            return new ObjectDataResponse(StatusCode.Update_Fail,"新增积分失败");
        }
    }

    @PostMapping("/list")
    @ApiOperation("获得积分规则列表")
    public PageResponse listIntegralChange(@RequestParam("status") IntegralChangeStatus integralChangeStatus){
        List<IntegralChange> integralChangeList=integralChangeService.listIntegralChange(integralChangeStatus);
        return new PageResponse(integralChangeList,integralChangeList.size());
    }

    @PostMapping("/update")
    @ApiOperation("更新积分规则")
    public ObjectDataResponse updateIntegralChange(@RequestBody  IntegralChange integralChange){
        Preconditions.checkNotNull(integralChange);
        Preconditions.checkArgument(integralChange.getId()>0);
        Integer flag=integralChangeService.updateIntegral(integralChange);
        if(flag>0) {
            return new ObjectDataResponse();
        }else{
            return new ObjectDataResponse(StatusCode.Update_Fail,"更新积分规则失败");
        }
    }

}
