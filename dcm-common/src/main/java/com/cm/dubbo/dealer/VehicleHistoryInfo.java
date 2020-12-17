package com.cm.dubbo.dealer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 历史车辆信息查询结果
 * @author popo
 * 2020-11-11
 */
@Builder
@Data
public class VehicleHistoryInfo {

    @ApiModelProperty("车辆VIN")
    private String vin;

    @ApiModelProperty("开始时间")
    private Date dateFrom;

    @ApiModelProperty("结束时间")
    private Date dateTo;

    @ApiModelProperty("历史数据")
    private List<VehicleInfo> history;

}
