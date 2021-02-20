package com.cm.datalog;

import com.cm.common.json.JacksonUtil;
import com.cm.common.utils.DateTimeUtils;
import com.cm.datalog.builder.BaseLogBuilder;
import com.cm.datalog.builder.HttpLogBuilder;
import com.cm.datalog.builder.LogBuilderFactory;
import com.cm.datalog.entity.DataLog;
import com.cm.datalog.sender.AbstractLogSender;
import com.cm.datalog.sender.MongoLogSender;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
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

    public static void send(DataLog dataLog){
        //确保日志记录不会引起线程中断
        try{
            logSender.send(dataLog);
        }catch(Exception e){
            log.error("log failed!", e);
        }

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
                                              Map<String, String> header,
                                              String body,
                                              int statusCode,
                                              String response,
                                              int duration){
        Date date = new Date();
        HttpLogBuilder logBuilder = LogBuilderFactory.createHttpLogBuilder().invokeBy(BaseLogBuilder.InvokeBy.SEND)
                .timestamp(date).url(url).header(header).reqBody(body)
                .code(statusCode).resBody(response).duration(duration);
        send(logBuilder.toLog());
    }

}
