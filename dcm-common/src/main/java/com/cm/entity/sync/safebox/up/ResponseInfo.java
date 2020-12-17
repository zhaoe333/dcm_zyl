package com.cm.entity.sync.safebox.up;

import lombok.Data;

import java.io.Serializable;

/**
 * 所有的命令响应的结果数据
 */
@Data
public class ResponseInfo implements Serializable {
    /**
     * 200为成功 其他为异常
     */
    private int status;
    /**
     * 对应command的事件type
     */
    private int eventType;
    /**
     * 异常情况下会返回的异常说明内容
     */
    private String msg;
}
