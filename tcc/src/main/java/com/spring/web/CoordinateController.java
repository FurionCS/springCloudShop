package com.spring.web;

import com.spring.domain.request.TccRequest;
import com.spring.service.TccService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Description 资源控制
 * @Author ErnestCheng
 * @Date 2017/6/2.
 */
@RestController
@RequestMapping(value="/tcc")
public class CoordinateController {

    @Autowired
    private TccService tccService;


    @ApiOperation(value="确认预留资源")
    @PostMapping(value="/coordinator/confirmation")
    public void confirm(@Valid @RequestBody TccRequest tccRequest, BindingResult result){
        tccService.confirm(tccRequest);
    }
}
