package com.cm.entity.sync.vs;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 从vs获取的车辆数据信息
 */
@Data
public class VehicleLocation implements Serializable {
    /**
     * vin
     */
    private String vin;
    /**
     * gps采集时间
     */
    private Date gpsTime;
    /**
     * 经度 火星坐标
     */
    private double lon;
    /**
     * 纬度 火星坐标
     */
    private double lat;
    /**
     * 经纬度是否可信 true可信 false未知
     */
    private boolean canBeTrusted;
    /**
     * 里程数
     */
    private Double odo;
}
