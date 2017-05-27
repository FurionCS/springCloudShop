package com.spring.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 测试加载
 * @Author ErnestCheng
 * @Date 2017/5/26.
 */
@RestController
@RefreshScope
public class configTest {

    @Value("${switch}")
    private String switchDate;
    @RequestMapping("/switchDate")
    public String switchDate() {
        return this.switchDate;
    }
}
