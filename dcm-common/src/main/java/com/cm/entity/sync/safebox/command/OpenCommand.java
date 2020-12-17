package com.cm.entity.sync.safebox.command;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 开门指令
 */
@Data
public class OpenCommand implements Serializable {
    /**
     * 蜂鸣器响的持续时间 ms
     */
    private int beepTime;
    /**
     * 指定蜂鸣器报警的时间 ms
     */
    private int alertTime;
    /**
     * 指定发送邮件的时间 ms
     */
    private int mailTime;
    /**
     * 指定开哪个门 为null或者size为0时 开所有门
     */
    private List<Integer> doorNumberList;
}
