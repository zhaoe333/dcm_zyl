package com.cm.entity.sync.safebox.up;

import lombok.Data;

import java.io.Serializable;

/**
 * 上线事件
 */
@Data
public class OnlineInfo implements Serializable {
    /**
     * safebox 版本号
     */
    private int versionCode;

}
