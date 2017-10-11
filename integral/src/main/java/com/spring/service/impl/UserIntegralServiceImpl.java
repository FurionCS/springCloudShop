package com.spring.service.impl;

import com.spring.persistence.UserIntegralMapper;
import com.spring.service.UserIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
@Service
public class UserIntegralServiceImpl implements UserIntegralService{

    @Autowired
    private UserIntegralMapper userIntegralMapper;

}
