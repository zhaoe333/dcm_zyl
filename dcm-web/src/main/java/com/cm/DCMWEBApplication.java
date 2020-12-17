package com.cm;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
//import com.cm.common.http.MyHttpClient;
//import com.cm.common.http.MyHttpClientBuilder;
//import com.cm.common.interceptor.PrivilegeInterceptor;
//import com.cm.common.spring.TSPMappingJackson2HttpMessageConverter;
//import com.cm.common.spring.TSPStringHttpMessageConverter;
//import com.cm.registry.ServerRegistryClient;
import com.cm.utils.MyHttpClient;
import com.cm.utils.MyHttpClientBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication(scanBasePackages={"com.cm"},exclude = {RedisAutoConfiguration.class,DruidDataSourceAutoConfigure.class,DataSourceAutoConfiguration.class, MongoAutoConfiguration.class})
@EnableScheduling
@MapperScan("com.cm.**.mapper")
@EnableConfigurationProperties
public class DCMWEBApplication extends WebMvcConfigurationSupport {


	/**
	 * 继承了WebMvcConfigurationSupport后 会导致WebMvcAutoConfiguration失效
	 * 从而导致spring.mvc.static-path-pattern和spring.resources.static-locations失效
	 * 因此需要手动配置静态文件访问路径
	 * 此项目暂时无用
	 * @param registry
	 */
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}


	/**
	 * 添加全局cors跨域设置
	 * @param registry
	 */
	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		super.addCorsMappings(registry);
		registry.addMapping("/**").allowedOrigins("*")
				.allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
				.allowedHeaders("*")
				.allowCredentials(false).maxAge(3600);
	}

	/**
	 * 实例化httpclient 用于请求第三方服务接口
	 * @return
	 */
	@Bean
	public MyHttpClient httpClientTemplate(){
		return MyHttpClientBuilder.create().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(DCMWEBApplication.class, args);
	}

}
