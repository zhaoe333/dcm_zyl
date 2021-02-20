package com.cm.datalog;

import com.cm.common.http.FMResponseCode;
import com.cm.common.json.JacksonUtil;
import com.cm.common.utils.DESUtils;
import com.cm.common.utils.DateTimeUtils;
import com.cm.common.utils.LogicUtil;
import com.cm.datalog.builder.BaseLogBuilder;
import com.cm.datalog.builder.HttpLogBuilder;
import com.cm.datalog.builder.LogBuilderFactory;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 *
 * 负责整体项目的数据采集
 * @author zyl
 * @createTime 2019-12-02
 */
@Component
@Slf4j
public class DataLogFilter implements Filter {
    /**
     * 心跳，接口文档，静态资源和下载文件不记录日志
     */
    private static final List<String> noLogList = Arrays.asList("api-docs", "static", "download");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            try{
                String uri = ((HttpServletRequest) request).getRequestURI();
                //  no log check
                if (inNoLogList(uri)) {
                    chain.doFilter(request, response);
                } else {
                    ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
                    ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
                    HttpLogBuilder httpLogBuilder = LogBuilderFactory.createHttpLogBuilder().invokeBy(BaseLogBuilder.InvokeBy.CLIENT_RECEIVE);
                    httpLogBuilder.setRequest(requestWrapper);
                    // Do normal servlet dispatch
                    chain.doFilter(requestWrapper, responseWrapper);
                    // Response
                    String content = new String(responseWrapper.getContentAsByteArray());
                    httpLogBuilder.resBody(content).resTime(new Date()).code(responseWrapper.getStatus());
                    // clientId
                    handleToken(getToken(requestWrapper, content), httpLogBuilder);
                    // Call this otherwise front-end won't receive the body content
                    responseWrapper.copyBodyToResponse();

                    DataLogHelper.send(httpLogBuilder.toLog());
                }
            }catch(Exception e){
                e.printStackTrace();
                log.error("http埋点数据出现异常， 请及时排查。", e);
            }
        }
    }

    private boolean inNoLogList(String uri){
        if(uri.equals("/")){
            return true;
        }
        for(String noLogPath:noLogList){
            if(uri.contains(noLogPath)){
                return true;
            }
        }
        return false;
    }

    private String getToken(ContentCachingRequestWrapper requestWrapper, String content) {
        // 以下为client信息获取
        String token = requestWrapper.getHeader("token");
        // 使用contains 避免项目名称影响判断
        // 这里需要处理 如果登录失败的返回信息
        if (requestWrapper.getRequestURI().contains("user/login")) {
            JsonNode bodyNode = JacksonUtil.get().json2Node(content);
            if(checkResponse(bodyNode)){
                token = bodyNode.get("body").get("token").asText();
            }
        }
        return token;
    }

    @Override
    public void destroy() {

    }

    private void handleToken(String token, HttpLogBuilder httpLogBuilder) throws Exception{
        if(LogicUtil.isNullOrEmpty(token)){
            return;
        }
        String[] userInfos = DESUtils.decrypt(token).split("-");
        httpLogBuilder.deviceType(userInfos[0]);
        httpLogBuilder.clientId(userInfos[2]);
    }

    private boolean checkResponse(JsonNode responseNode){
        return null != responseNode.get("code") && responseNode.get("code").textValue().equals(FMResponseCode.normal.getCode());
    }
}
