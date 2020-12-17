package com.cm.dubbo.dealer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 围栏信息查询结果
 * @author popo
 * @createTime 2020-9-16
 */
@Builder
@Data
public class GeoFenceInfo {

    @ApiModelProperty("围栏ID")
    private long id;

//    @ApiModelProperty("仓库ID")
//    private String warehouseId;

    @ApiModelProperty("围栏名称")
    private String name;

//    @ApiModelProperty("围栏类型: circle|rect|polygon")
//    private String shape;

//    @ApiModelProperty("围栏坐标: circle(center,radius)|rect(southwest,northeast)|polygon(point list)")
//    private String location;

    @ApiModelProperty("圆心坐标: [lon,lat]")
    private Double[] center;

    @ApiModelProperty("半径(米)")
    private Integer radius;

//    @ApiModelProperty("矩形西南角坐标")
//    private Double[] southwest;
//
//    @ApiModelProperty("矩形东北角坐标")
//    private Double[] northeast;
//
//    @ApiModelProperty("多边形坐标列表")
//    private List<Double[]> points;

//    @ApiModelProperty("围栏状态: 0--有效; 1--无效")
//    private Integer enabled;

    @ApiModelProperty("围栏内车辆数")
    private Integer vehicleCount;

    @ApiModelProperty("最近检查时间")
    private Date checkingTime;

    // 仓库信息
//    private WarehouseInfo warehouse;

}
