package com.cm.dubbo.safebox;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * SafeBox查询结果
 * @author pengtao
 * 2020-12-2
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SafeBoxRuleInfo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("经销商code")
    private Long dealerCode;

    @ApiModelProperty("经销商名字")
    private String dealerName;

    @ApiModelProperty("CRS等级")
    private int CRS;

    @ApiModelProperty("经销商集团")
    private String group;

    @ApiModelProperty("最多解绑次数")
    private int rebindingTimes;

    @ApiModelProperty("关柜子的开始时间")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty("关柜子的结束时间")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty("是否锁的状态 0--normal，1--locked")
    private int remoteLock;
}
