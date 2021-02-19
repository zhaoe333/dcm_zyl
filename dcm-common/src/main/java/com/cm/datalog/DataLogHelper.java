package com.cm.datalog;

import com.cm.common.json.JacksonUtil;
import com.cm.common.utils.DateTimeUtils;
import com.cm.datalog.sender.AbstractLogSender;
import com.cm.datalog.sender.MongoLogSender;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.core.KafkaTemplate;

/**
 * TODO 调整为kafka记录 暂未优化
 */
@Slf4j
public class DataLogHelper {

    private static AbstractLogSender logSender;

    private static AbstractLogSender getLogSender(){
        if(null!=logSender){
            return logSender;
        }
        logSender = new MongoLogSender(true);
        return logSender;
    }

    public static void send(BaseLog baseLog){
        //确保日志记录不会引起线程中断
        try{
            logSender.send(baseLog);
        }catch(Exception e){
            log.error("log failed!", e);
        }

    }

    /**
     * General http tracing log
     * @param tracingId
     * @return
     */
    public static TracingLog.Builder createTracingLog(String tracingId) {
        return new TracingLog.Builder()
                .tracingId(tracingId);
    }

    /**
     * Internel http request/response log
     * @param url
     * @param header
     * @param body
     * @param statusCode
     * @param response
     * @param duration
     */
    public static void sendInternalTracingLog(String url,
                                              Object header,
                                              Object body,
                                              int statusCode,
                                              String response,
                                              int duration)
    {
        TracingLog.Builder b = new TracingLog.Builder()
                .tracingId(String.valueOf(Thread.currentThread().getId()))
                .timestamp(DateTimeUtils.formatDate())
                .reqURL(url)
                .reqHeader(header != null ? (header instanceof String?header.toString(): JacksonUtil.get().readAsString(header)): null)
                .reqBody(body != null ? (body instanceof String?body.toString(): JacksonUtil.get().readAsString(body)) : null)
                .respStatusCode(statusCode)
                .respContent(response)
                .duration(duration)
                .invokeBy("internal");
        send(b.build());
    }

}
