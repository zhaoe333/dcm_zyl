package com.cm.datalog.sender;

import com.cm.common.json.JacksonUtil;
import com.cm.datalog.entity.DataLog;

public abstract class StringLogSender extends AbstractLogSender {

    public StringLogSender(boolean localLog) {
        super(localLog);
    }

    /**
     * 记录baselog
     * @param dataLog
     * @return
     */
    public boolean send(DataLog dataLog){
        String message = JacksonUtil.get().readAsString(dataLog);
        doLocalLog(message);
        return doSend(message);
    }

    /**
     * 记录纯文本日志
     * @param message
     * @return
     */
    protected abstract boolean doSend(String message);

}
