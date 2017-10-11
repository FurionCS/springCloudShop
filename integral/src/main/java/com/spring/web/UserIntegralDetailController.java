package com.spring.web;

import com.spring.service.UserIntegralDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
@RestController
@RequestMapping("/user/integral/detail")
public class UserIntegralDetailController {

    @Autowired
    private UserIntegralDetailService userIntegralDetailService;
}
