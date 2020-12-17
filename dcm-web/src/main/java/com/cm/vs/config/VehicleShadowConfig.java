package com.cm.vs.config;

import com.cm.vs.handle.VehicleShadowHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * vs配置类
 */
@Data
@Configuration
@Slf4j
public class VehicleShadowConfig {

    @Bean
    @ConfigurationProperties(prefix = "dcm.vs")
    public VehicleShadowProperties vehicleShadowProperties() {
        return new VehicleShadowProperties();
    }

    @Bean
    public VehicleShadowHandler vehicleShadowHandler(){
        return new VehicleShadowHandler();
    }
}
