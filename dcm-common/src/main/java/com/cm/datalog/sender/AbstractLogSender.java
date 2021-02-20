package com.cm.datalog.sender;

import com.cm.datalog.entity.DataLog;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 记录日志的实现类
 */
@Slf4j
@Data
public abstract class AbstractLogSender {
    /**
     * 是否记录本地日志
     */
    private boolean localLog;

    public AbstractLogSender(boolean localLog) {
        this.localLog = localLog;
    }

    /**
     * 记录纯文本日志
     * @param dataLog
     * @return
     */
    public abstract boolean send(DataLog dataLog);

    protected void doLocalLog(String message){
        if(localLog){
            log.info(message);
        }
    }
}
