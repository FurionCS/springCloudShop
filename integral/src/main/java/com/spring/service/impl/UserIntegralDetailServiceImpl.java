package com.spring.service.impl;

import com.spring.persistence.UserIntegralDetailMapper;
import com.spring.service.UserIntegralDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ErnestCheng on 2017/10/11.
 */
@Service
public class UserIntegralDetailServiceImpl implements UserIntegralDetailService {

    @Autowired
    private UserIntegralDetailMapper userIntegralDetailMapper;
}
