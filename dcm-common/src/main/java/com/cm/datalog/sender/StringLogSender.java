package com.cm.datalog.sender;

import com.cm.common.json.JacksonUtil;
import com.cm.datalog.BaseLog;

public abstract class StringLogSender extends AbstractLogSender {

    public StringLogSender(boolean localLog) {
        super(localLog);
    }

    /**
     * 记录baselog
     * @param baseLog
     * @return
     */
    public boolean send(BaseLog baseLog){
        String message = JacksonUtil.get().readAsString(baseLog);
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
