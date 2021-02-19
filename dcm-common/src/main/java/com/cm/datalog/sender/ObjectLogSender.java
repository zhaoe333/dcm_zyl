package com.cm.datalog.sender;

import com.cm.common.json.JacksonUtil;
import com.cm.datalog.BaseLog;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ObjectLogSender extends AbstractLogSender {

    public ObjectLogSender(boolean localLog) {
        super(localLog);
    }

    @Override
    public boolean send(BaseLog baseLog) {
        if(isLocalLog()){
            log.info(JacksonUtil.get().readAsString(baseLog));
        }
        return doSend(baseLog);
    }

    /**
     * 记录纯文本日志
     * @param baseLog
     * @return
     */
    protected abstract boolean doSend(BaseLog baseLog);
}
