package com.cm.dubbo.violation;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 违规记录查询结果
 * @author popo
 * 2020-11-13
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealerTrackingViolationInfo {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("车辆VIN码")
    private String vin;

    @ApiModelProperty("经销商代码")
    private String dealerCode;

    @ApiModelProperty("经销商名称")
    private String dealerName;

    @ApiModelProperty("违规时间")
    private Date violationTime;

    @ApiModelProperty("类型（目前无数据）")
    private Integer violationType;

    @ApiModelProperty("状态（目前无数据）")
    private Integer violationStatus;

}
