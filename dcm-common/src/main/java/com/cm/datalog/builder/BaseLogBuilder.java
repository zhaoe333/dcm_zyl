package com.cm.datalog.builder;

import com.cm.datalog.entity.ClientInfo;
import com.cm.datalog.entity.DataLog;
import com.cm.datalog.entity.LocationInfo;
import io.swagger.models.auth.In;

import java.util.Date;

public abstract class BaseLogBuilder<T extends BaseLogBuilder<T>> {

    protected DataLog dataLog;

    public enum LogType{
        HTTP,// http日志
        TASK,// 系统任务执行日志
        TCP,// tcp交互日志 包括dubbo rpc mq等
        COMMON;// 普通日志 需要记录在数据库中的
    }

    public enum InvokeBy{
        LOCAL,// 本地日志
        SEND,// 请求第三方的日志
        RECEIVE,// 接收第三方的日志
        CLIENT_RECEIVE;// 接收用户的请求日志
    }

    public BaseLogBuilder(){
        dataLog = new DataLog();
        dataLog.setClient(new ClientInfo());
    }

    public DataLog toLog(){
        if(null==dataLog.getTimestamp()){
            dataLog.setTimestamp(new Date());
        }
        return dataLog;
    }

    /**
     * 返回对象本身
     * @return builder
     */
    public abstract T self();

    /**
     * 设置clientId
     * @param clientId client id
     * @return builder
     */
    public T clientId(String clientId){
        dataLog.getClient().setClientId(clientId);
        return self();
    }

    public T deviceInfo(String deviceId, String deviceSystem){
        dataLog.getClient().setDeviceId(deviceId);
        dataLog.getClient().setDeviceSystem(deviceSystem);
        return self();
    }

    public T deviceType(String deviceType){
        dataLog.getClient().setDeviceType(deviceType);
        return self();
    }

    public T message(String message){
        dataLog.setMessage(message);
        return self();
    }

    public T duration(long duration){
        dataLog.setDuration(duration);
        return self();
    }

    public T invokeBy(InvokeBy invokeBy){
        dataLog.setInvokeBy(invokeBy);
        return self();
    }

    public T logType(LogType logType){
        dataLog.setLogType(logType);
        return self();
    }

    public T timestamp(Date timestamp){
        dataLog.setTimestamp(timestamp);
        return self();
    }

    public T hostname(String hostname){
        dataLog.setHostname(hostname);
        return self();
    }

    public T location(double lon, double lat){
        dataLog.setLocation(new LocationInfo(lat, lon));
        return self();
    }

}
