package com.cm.dubbo.sync.vs;

import com.cm.entity.sync.vs.VehicleLocation;

import java.util.List;

/**
 * 车辆坐标获取的接口
 */
public interface IVehicleLocationService {
    /**
     * 批量获取车辆的坐标
     * @param vinList vinlist
     * @return 这里返回的坐标是火星坐标 高德可用
     */
    List<VehicleLocation> queryVehicleLocation(List<String> vinList);
}
