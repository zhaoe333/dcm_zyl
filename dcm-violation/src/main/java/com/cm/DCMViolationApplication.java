package com.cm;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication(scanBasePackages={"com.cm"},exclude = {RedisAutoConfiguration.class, DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class, MongoAutoConfiguration.class})
@EnableScheduling
@MapperScan("com.cm.**.mapper")
@EnableConfigurationProperties
public class DCMViolationApplication extends WebMvcConfigurationSupport {

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

    public static void main(String[] args) {
        SpringApplication.run(DCMViolationApplication.class, args);
    }
}
