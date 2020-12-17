package com.cm.dubbo.violation;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * GPS规则查询结果
 * @author popo
 * 2020-11-12
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingRulesInfo {

    @ApiModelProperty("规则ID")
    private long id;

    @ApiModelProperty("规则名称")
    private String name;

    @ApiModelProperty("规则类别: 0--固定时间; 1--间隔时间")
    private int type;

    @ApiModelProperty(value = "第一次执行时间(HH:mm) (type=0)", example = "10:00")
    @JsonFormat(pattern = "HH:mm",timezone = "GMT+8")
    private Date firstTime;

    @ApiModelProperty(value = "第二次执行时间(HH:mm) (type=0)", example = "22:00")
    @JsonFormat(pattern = "HH:mm",timezone = "GMT+8")
    private Date secondTime;

    @ApiModelProperty("执行频率(分钟) (type=1)")
    private Integer frequency;

    //"经销商id")
    private String dealerId;

    //"时间类型"
    private String executionTime;
}
