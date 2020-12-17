package com.cm.entity.sync.safebox.up;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 盘库数据
 */
@Data
public class CheckInfo implements Serializable {
    /**
     * 4个门内的数据信息
     */
    private List<DoorInfo> doorRfids;
}

@Data
class DoorInfo implements Serializable {
    /**
     * 1-左上角柜门、2-右上角柜门、3-左下角柜门、4-右下角柜门
     */
    private int doorNumber;
    /**
     * 门内的rfid的记录
     */
    private List<RfidInfo> rfids;
}

@Data
class RfidInfo implements Serializable {
    /**
     * rfid的值
     */
    private String rfid;
    /**
     * 在门内的哪一层
     */
    private int layer;
}
