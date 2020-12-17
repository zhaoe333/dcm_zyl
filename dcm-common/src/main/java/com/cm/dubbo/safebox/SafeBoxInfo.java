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
public class SafeBoxInfo {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("保险柜code")
    private Long safeBoxCode;

    @ApiModelProperty("文档数量")
    private int docNum;

    @ApiModelProperty("key数量")
    private int keyNum;

    @ApiModelProperty("保险柜门状态 0--open，1--close")
    private int doorStatus;

    @ApiModelProperty("关柜子的开始时间")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty("关柜子的结束时间")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty("是否锁的状态 0--normal，1--locked")
    private int lockStatus;

    @ApiModelProperty("最后操作人")
    private String lastContact;

    @ApiModelProperty("最后锁的时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastContactTime;

    @ApiModelProperty("是否在圈内 0--out，1--in")
    private int safeBoxStatus;

    @ApiModelProperty("围栏信息")
    private String eFence;
}
