package com.cm.entity.sync.safebox.command;

import lombok.Data;

import java.io.Serializable;

/**
 * 发送指令 立刻触发蜂鸣器响
 */
@Data
public class WarnCommand implements Serializable {
    /**
     * 蜂鸣器响的持续时间
     */
    private int beepTime;
}
