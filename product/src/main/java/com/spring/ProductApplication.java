package com.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringCloudApplication
@EnableCaching
@EnableFeignClients
@MapperScan(basePackages = "com.spring.persistence")
@RefreshScope //此项必须加，否则即使请求/bus/refresh依然不刷新
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
}
