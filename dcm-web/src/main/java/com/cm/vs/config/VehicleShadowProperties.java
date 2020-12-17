package com.cm.vs.config;

import lombok.Data;

@Data
public class VehicleShadowProperties {
    /**
     * token
     */
    private String keyId;
    /**
     * vs
     * 访问url
     */
    private String url;
    /**
     * basic授权用户名
     */
    private String username;
    /**
     * basix授权密码
     */
    private String password;
    /**
     * 失败重试次数
     */
    private int retryNum = 5;
}
