package com.spring.web;

import com.spring.domain.request.TccRequest;
import com.spring.service.TccService;

import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description 资源控制
 * @Author ErnestCheng
 * @Date 2017/6/2.
 */
@RestController
@RequestMapping(value="/tcc")
public class CoordinateController {

    private static final Logger logger= Logger.getLogger(CoordinateController.class);

    @Autowired
    private TccService tccService;


    @ApiOperation(value="确认预留资源")
    @PostMapping(value="/coordinator/confirmation")
    public void confirm(@Valid @RequestBody TccRequest tccRequest, BindingResult result){
        tccService.confirm(tccRequest);
    }

    @ApiOperation(value="取消预留资源")
    @PostMapping(value="/coordinator/cancellation")
    public void cancel(@Valid @RequestBody TccRequest tccRequest,BindingResult result){
        logger.info("取消预留资源");
        tccService.cancel(tccRequest);
    }
}
