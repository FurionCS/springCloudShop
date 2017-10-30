package com.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringCloudApplication
@EnableFeignClients
@MapperScan(basePackages = "com.spring.persistence")
@ServletComponentScan
@RefreshScope //此项必须加，否则即使请求/bus/refresh依然不刷新
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
}
