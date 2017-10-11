package com.spring.web;

import com.spring.service.UserIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
@RestController
@RequestMapping("/user/Integral")
public class UserIntegralController {

    @Autowired
    private UserIntegralService userIntegralService;
}
