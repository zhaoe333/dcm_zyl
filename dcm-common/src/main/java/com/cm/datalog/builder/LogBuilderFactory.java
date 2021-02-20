package com.cm.datalog.builder;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 日志构建工厂类
 * 用来创建logBuilder
 */
@Slf4j
public class LogBuilderFactory {

    private static final String COMMON = "common";

    private static final String HTTP = "http";

    private static final String TCP = "tcp";

    private static final String HOSTNAME = getHostname();

    private static String getHostname(){
        //获取本机hostname
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            log.warn("无法获取hostname.", e);
        }
        return null;
    }

    public static HttpLogBuilder createHttpLogBuilder(){
        return new HttpLogBuilder().hostname(HOSTNAME).logType(BaseLogBuilder.LogType.HTTP);
    }
}
