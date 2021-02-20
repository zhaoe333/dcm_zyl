package com.cm.datalog.entity;

/**
 * 日之类
 */

import com.cm.datalog.builder.BaseLogBuilder;
import lombok.Data;

import java.util.Date;

@Data
public class DataLog {

    private ClientInfo client;      // Person, Vehicle (Who)
    private LocationInfo location;  // Location (Where)
    private RequestInfo request;   // request info
    private ResponseInfo response;  // response info

    private String tracingId;		// tracingId 暂时不用
    private Date timestamp;       // Request time (When) 日志记录的时间点
    private long duration;           // 请求的响应时长 默认为0
    private BaseLogBuilder.LogType logType;            // http请求日志，task执行日志，tcp交互日志，common普通文本日志
    private String hostname;        // 服务所在的hostname
    private BaseLogBuilder.InvokeBy invokeBy;        // local系统自身的日志，send系统请求其他方的日志，receive系统接收
    private String message;         // 普通日志内容 或者备注的一些信息

}
