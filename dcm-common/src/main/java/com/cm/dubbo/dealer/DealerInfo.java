package com.cm.dubbo.dealer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 经销商列表/详情查询结果
 * @author popo
 * @createTime 2020-9-15
 */
@Builder
@Data
public class DealerInfo {

    @ApiModelProperty("经销商ID")
    private long id;

    @ApiModelProperty("经销商代码")
    private String code;

    @ApiModelProperty("经销商名称")
    private String name;

    @ApiModelProperty("经销商等级")
    private String rating;

    @ApiModelProperty("所在区域")
    private String region;

    @ApiModelProperty("集团名称")
    private String group;

//    @ApiModelProperty("仓库数量")
//    private Integer warehouseCount;
//
//    @ApiModelProperty("外展数量")
//    private Integer exhibitionCount;

//    @ApiModelProperty("仓库信息")
//    private List<WarehouseInfo> warehouseList;

//    @ApiModelProperty("围栏信息")
//    private List<GeoFenceInfo> fenceList;
//
//    @JsonIgnore
//    @ApiModelProperty("操作员ID")
//    private String operatorId;
//
//    @JsonIgnore
//    @ApiModelProperty("创建时间")
//    private Date createTime;
//
//    @ApiModelProperty("最后更新时间")
//    private Date updateTime;
//
//    @JsonIgnore
//    @ApiModelProperty("删除标识 0--正常; 1--删除")
//    private Integer deleteFlag;
//
//    @ApiModelProperty("GPS最后更新时间(test only)")
//    private Date gpsUpdatingTime;

}
