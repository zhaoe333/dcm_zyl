package com.cm.dubbo.violation;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * GPS规则查询结果
 * @author popo
 * 2020-11-12
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealerTrackingRulesInfo implements Serializable {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("规则ID")
    private Long ruleId;

    @ApiModelProperty("规则名称")
    private String ruleName;

    @ApiModelProperty("经销商ID")
    private Long dealerId;

    @ApiModelProperty("经销商代码")
    private String dealerCode;

    @ApiModelProperty("经销商名称")
    private String dealerName;

    @ApiModelProperty("经销商等级")
    private String dealerRating;

    @ApiModelProperty("所在区域")
    private String dealerRegion;

    @ApiModelProperty("集团名称")
    private String dealerGroup;

    @ApiModelProperty("规则类型")
    private String ruleType;
}
