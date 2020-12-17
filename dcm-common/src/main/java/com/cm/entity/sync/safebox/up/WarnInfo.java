package com.cm.entity.sync.safebox.up;

import lombok.Data;

import java.io.Serializable;

/**
 * 报警信息
 */
@Data
public class WarnInfo implements Serializable {
    /**
     * 目前只有两种报警
     * 1. 蜂鸣器响 2。需要发送邮件
     */
    private int alertType;
}
