package com.cm.entity.sync.safebox.command;

import lombok.Data;

import java.io.Serializable;

/**
 * 远程升级指令
 */
@Data
public class OTACommand implements Serializable {
    /**
     * 版本号
     */
    private int apkVersionCode;
    /**
     * 软件升级路径
     */
    private String apkFullUrl;
}
