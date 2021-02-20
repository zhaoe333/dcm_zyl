package com.cm.datalog.entity;

import lombok.Data;

import java.util.Date;

/**
 * 响应结果
 */
@Data
public class ResponseInfo {

    private String header;		// Response headers
    private String body;		// Response body
    private Date resTime;	// Response time
    private int code;        // Response code

}
