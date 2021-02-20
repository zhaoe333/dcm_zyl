package com.cm.datalog.sender;

import com.cm.common.json.JacksonUtil;
import com.cm.datalog.entity.DataLog;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ObjectLogSender extends AbstractLogSender {

    public ObjectLogSender(boolean localLog) {
        super(localLog);
    }

    @Override
    public boolean send(DataLog dataLog) {
        if(isLocalLog()){
            log.info(JacksonUtil.get().readAsString(dataLog));
        }
        return doSend(dataLog);
    }

    /**
     * 记录纯文本日志
     * @param dataLog
     * @return
     */
    protected abstract boolean doSend(DataLog dataLog);
}
