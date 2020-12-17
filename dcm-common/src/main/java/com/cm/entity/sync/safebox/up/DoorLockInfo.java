package com.cm.entity.sync.safebox.up;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 门锁状态上报
 */
@Data
public class DoorLockInfo implements Serializable {
    /**
     * 1 所有柜门都关闭 0至少有1个门打开
     */
    private int cabinetStatus;
    /**
     * 每个门的状态
     */
    private List doorLockStatus;
}

@Data
class DoorLockStatus implements Serializable {
    /**
     * 1-左上角柜门、2-右上角柜门、3-左下角柜门、4-右下角柜门
     */
    private int doorNumber;
    /**
     * true 开  false 关闭
     */
    private boolean isOpen;
}
