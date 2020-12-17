package com.cm.dubbo.dealer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 仓库信息查询结果
 * @author popo
 * @createTime 2020-9-15
 */
@Builder
@Data
public class WarehouseInfo {

    @ApiModelProperty("仓库ID")
    private long id;

    @ApiModelProperty("经销商")
    private DealerInfo dealer;

//    @JsonIgnore
//    @ApiModelProperty("围栏ID")
//    private String fenceId;

    @ApiModelProperty("围栏")
    private GeoFenceInfo geoFence;

    @ApiModelProperty("仓库代码")
    private String code;

    @ApiModelProperty("仓库类型: [4S, 4S-1, Showroom, Cross Location, Exhibition]")
    private String locationType;

    @ApiModelProperty("仓库地址")
    private String address;

    @ApiModelProperty("仓库位置: [lon, lat]")
    private Double[] location;

    @ApiModelProperty("资产类别: [Land Use Right, Lease Agreement]")
    private String propertyType;

    @ApiModelProperty("仓库状态: [Active, inactive]")
    private String locationStatus;

    @ApiModelProperty("有效期开始")
    private Date startingDate;

    @ApiModelProperty("有效期截止")
    private Date expiringDate;

    @ApiModelProperty("数据来源: [DCM, CMS]")
    private String source;

//    @ApiModelProperty("仓库状态: true--已到期; false--未到期")
//    private Boolean isAvailable;

}
