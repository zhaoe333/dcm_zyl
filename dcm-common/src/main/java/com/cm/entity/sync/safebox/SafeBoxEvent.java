package com.cm.entity.sync.safebox;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础事件类 里面包含通用的数据
 */
@Data
public class SafeBoxEvent<T> implements Serializable {
    /**
     * 保险柜编码
     */
    private String safeBoxCode;
    /**
     * 事件触发的时间
     */
    private Date timestamp;
    /**
     * 用来识别指令的相应包
     */
    private String msgId;
    /**
     * 事件类型
     */
    private int eventType;
    /**
     * 数据 根据不同的eventType获取消息体类型
     */
    private T data;
}
