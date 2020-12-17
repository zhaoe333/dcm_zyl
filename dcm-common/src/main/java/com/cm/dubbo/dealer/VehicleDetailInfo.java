package com.cm.dubbo.dealer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 车辆详情页查询结果
 * @author popo
 * @createTime 2020-9-28
 */
@Builder
@Data
public class VehicleDetailInfo {

    @ApiModelProperty("车辆信息")
    private List<VehicleInfo> vehicleList;

    @ApiModelProperty("仓库信息")
    private List<WarehouseInfo> warehouseList;

}
