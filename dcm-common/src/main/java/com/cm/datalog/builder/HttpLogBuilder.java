package com.cm.datalog.builder;

import com.cm.common.json.JacksonUtil;
import com.cm.common.utils.DateTimeUtils;
import com.cm.datalog.entity.ClientInfo;
import com.cm.datalog.entity.RequestInfo;
import com.cm.datalog.entity.ResponseInfo;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * http 日志记录类
 */
@Slf4j
public class HttpLogBuilder extends BaseLogBuilder<HttpLogBuilder>{

    private static final List<String> headerFilter = Arrays.asList("token", "Content-Type", "User-Agent");
    private static final String LOCAL_IP = "127.0.0.1";
    private static final String LOCAL_GATEWAY = "0:0:0:0:0:0:0:1";
    private static final String UNKNOWN = "unknown";

    public HttpLogBuilder(){
        super();
        dataLog.setRequest(new RequestInfo());
        dataLog.setResponse(new ResponseInfo());
    }

    @Override
    public HttpLogBuilder self() {
        return this;
    }

    public HttpLogBuilder reqBody(String reqBody){
        dataLog.getRequest().setBody(reqBody);
        return this;
    }

    public HttpLogBuilder resBody(String resBody){
        dataLog.getResponse().setBody(resBody);
        return this;
    }

    public HttpLogBuilder method(String method){
        dataLog.getRequest().setMethod(method);
        return this;
    }

    public HttpLogBuilder reqTime(Date reqTime){
        dataLog.getRequest().setReqTime(reqTime);
        return this;
    }

    public HttpLogBuilder resTime(Date resTime){
        dataLog.getResponse().setResTime(resTime);
        // 计算duration
        if(0==dataLog.getDuration()&&null!=dataLog.getRequest().getReqTime()){
            this.duration(resTime.getTime() - dataLog.getRequest().getReqTime().getTime());
        }
        return this;
    }

    public HttpLogBuilder host(String host){
        dataLog.getRequest().setHost(host);
        return this;
    }

    public HttpLogBuilder url(String url){
        dataLog.getRequest().setUrl(url);
        return this;
    }

    public HttpLogBuilder header(Map<String, String> header){
        dataLog.getRequest().setHeader(header);
        return this;
    }

    public HttpLogBuilder code(int code){
        dataLog.getResponse().setCode(code);
        return this;
    }

    public HttpLogBuilder setRequest(ContentCachingRequestWrapper request){
        String uri = request.getRequestURI();
        String method = request.getMethod();
        // Request headers
        Map<String, String> headers = new HashMap<>();
        Collections.list(request.getHeaderNames()).forEach(headerName ->{
            if(headerFilter.contains(headerName)){
                headers.put(headerName, String.join(",", Collections.list(request.getHeaders(headerName))));

            }
        });
        return this.method(method).url(uri).header(headers)
                .reqBody(new String(request.getContentAsByteArray()))
                .reqTime(new Date()).host(getIpAddr(request));
    }

    public HttpLogBuilder setResponse(ContentCachingResponseWrapper response){
        // Response
        String content = new String(response.getContentAsByteArray());
        this.resBody(content).resTime(new Date()).code(response.getStatus());
        return this;
    }

    private String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(LOCAL_IP.equals(ipAddress) || LOCAL_GATEWAY.equals(ipAddress)){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.warn("Unknown Host: ",e);
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割 ipv6不支持
        //"***.***.***.***".length() = 15
        if(ipAddress != null && ipAddress.length() > 15){
            if(ipAddress.indexOf(",") > 0){
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

}
