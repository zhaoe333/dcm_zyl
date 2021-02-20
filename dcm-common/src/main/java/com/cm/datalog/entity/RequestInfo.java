package com.cm.datalog.entity;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class RequestInfo {

    private String method;		// http  Request method
    private String url;			// Complete request URL 如果非http请求 可以自行填写方法名称等内容来标记请求日志
    private Map<String, String> header;		// Request headers
    private String body;		// Request body
    private String host;		// Request host
    private Date reqTime;	// Request time
    private Object extra;       // Reservation

}
