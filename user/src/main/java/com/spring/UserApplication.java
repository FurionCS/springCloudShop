package com.spring;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringCloudApplication
@EnableCaching
@EnableFeignClients
@MapperScan(basePackages = "com.spring.persistence")
@ServletComponentScan
public class UserApplication {
	@Bean
	public RedisTemplate<String, ?> redisTemplate(RedisConnectionFactory factory) {
			Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
			ObjectMapper om = new ObjectMapper();
			om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
			om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
			jackson2JsonRedisSerializer.setObjectMapper(om);
			RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
			//定义key序列化方式
			RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型会出现异常信息;需要我们上面的自定义key生成策略，一般没必要
			template.setConnectionFactory(factory);
			template.setKeySerializer(redisSerializer);
			template.setValueSerializer(jackson2JsonRedisSerializer);
			template.setHashKeySerializer(redisSerializer);
			template.setHashValueSerializer(jackson2JsonRedisSerializer);
			template.afterPropertiesSet();
			return template;
	}
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}
}
