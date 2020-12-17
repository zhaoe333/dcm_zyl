package com.cm.dubbo.audit;

import com.cm.dubbo.dealer.DealerInfo;
import com.cm.dubbo.dealer.GeoFenceInfo;
import com.cm.dubbo.dealer.WarehouseInfo;
import com.cm.dubbo.safebox.SafeBoxInfo;
import com.cm.dubbo.sys.UserInfo;
import com.cm.dubbo.violation.DealerTrackingRulesInfo;
import com.cm.dubbo.violation.TrackingRulesInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 审批信息查询结果
 * @author popo
 * 2020-11-24
 */
@Builder
@Data
public class AuditInfo {
    @ApiModelProperty("Task ID")
    private Long id;

    @ApiModelProperty("Task Type: [" +
            " 0--Fence in Approval Process;" +
            " 1--Delete Location Type Approval Process;" +
            " 2--Dealer Matches GPS Tracking Rule Task;" +
            " 3--Location Type and Fence Approval Process;" +
            " 4--GPS Tracking Rule Change Process;" +
            " 5--Long Distance Relocation Process;" +
            " 6--Safe-Box Automatic Locking Setting;" +
            " 7--Safe-Box And Dealer Association " +
            " 8--Safe-Box And Dealer Deassociation " +
            " 9--Remote Locking of Safe-Box]")
    private Integer type;

    @ApiModelProperty("Application Date")
    private Date applicationDate;

    @ApiModelProperty("Approer")
    private UserInfo approver;

    @ApiModelProperty("Applicant")
    private UserInfo applicant;

    @ApiModelProperty("Status: [0--Pending; 1--Approved; 2--Declined; 3--Rejected]")
    private Integer status;

    @ApiModelProperty("Remark")
    private String remark;

    @ApiModelProperty("Reason")
    private String reason;

    @ApiModelProperty("New or Update?")
    private Boolean isNew;

    @ApiModelProperty("经销商信息")
    private DealerInfo dealer;

    @ApiModelProperty("仓库信息")
    private WarehouseInfo warehouse;

    @ApiModelProperty("GPS规则信息")
    private TrackingRulesInfo trackingRule;

    @ApiModelProperty("GPS规则信息")
    private List<DealerTrackingRulesInfo> dealerTrackingRules;

    @ApiModelProperty(value = "查询时间 结束时间 HH:mm", example = "23:00")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "查询时间 开始时间 HH:mm", example = "07:00")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty("经销商信息集合")
    private List<DealerInfo> dealerInfos;

    @ApiModelProperty("保险柜code")
    private SafeBoxInfo safeBox;

    @ApiModelProperty("围栏信息")
    private GeoFenceInfo fenceInfo;

    @ApiModelProperty("锁的状态 0--no 1--yes")
    private String lock;
}
