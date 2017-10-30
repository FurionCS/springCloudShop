package com.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringCloudApplication
@MapperScan(basePackages = "com.spring.persistence")
@RefreshScope//此项必须加，否则即使请求/bus/refresh依然不刷新
public class IntegralApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegralApplication.class, args);
	}
}
