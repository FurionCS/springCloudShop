package com.spring.web;

import com.google.common.base.Preconditions;
import com.spring.common.model.StatusCode;
import com.spring.common.model.response.ObjectDataResponse;
import com.spring.common.model.response.PageResponse;
import com.spring.domain.dto.IntegralChangeDTO;
import com.spring.domain.model.IntegralChange;
import com.spring.domain.type.IntegralChangeStatus;
import com.spring.service.IntegralChangeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
    public ObjectDataResponse addIntegralChange(@Validated @RequestBody IntegralChangeDTO integralChangeDTO, BindingResult result){
        IntegralChange integralChange=integralChangeDTO.convertToIntegralChange();
        integralChange.setUpdateTime(Timestamp.valueOf(LocalDateTime.now())).setCreateTime(integralChange.getUpdateTime());
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
