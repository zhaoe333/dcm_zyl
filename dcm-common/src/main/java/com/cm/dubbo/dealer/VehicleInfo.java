package com.cm.dubbo.dealer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 车辆信息查询结果
 * @author popo
 * 2020-11-11
 */
@Builder
@Data
public class VehicleInfo {

    @ApiModelProperty("车辆ID")
    private long id;

    @ApiModelProperty("经销商")
    private DealerInfo dealer;

//    @ApiModelProperty("仓库ID")
//    private WarehouseBean warehouse;

    @ApiModelProperty("车辆VIN码")
    private String vin;

    @ApiModelProperty("车辆型号")
    private String model;

    @ApiModelProperty("车辆颜色")
    private String color;

    @ApiModelProperty("车辆价格")
    private String price;

    @ApiModelProperty("车辆激活日期")
    private Date activateDate;

    @ApiModelProperty("车辆贷款状态: [Current, Sold Out, Settled, Recall Stock, Transferred]")
    private String loanStatus;

    @ApiModelProperty("Vehicle financed condition: [New, ...]")
    private String assetCondition;

    @ApiModelProperty("Title document release type: [Normal, Fast]")
    private String releaseType;

    @ApiModelProperty("车辆状态: 0--在围栏; 1--出围栏; 2--No singal; 3--In transit")
    private Integer vehicleStatus;

    @ApiModelProperty("文档状态: 0--正常; 1--异常")
    private Integer docStatus;

    @ApiModelProperty("钥匙状态: 0--正常; 1--异常")
    private Integer keyStatus;

    @ApiModelProperty("GPS检查时间")
    private Date checkingTime;

    @ApiModelProperty("系统检查时间")
    private Date queryingTime;

    @ApiModelProperty("车辆位置")
    private Double[] location;

    @ApiModelProperty("是否迁移")
    private Boolean relocating;

}
