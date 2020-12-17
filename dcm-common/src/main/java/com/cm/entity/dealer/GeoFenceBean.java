package com.cm.entity.dealer;

import com.cm.entity.CommonBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 电子围栏信息EntityBean
 * @author popo
 * 2020-11-5
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoFenceBean extends CommonBean implements java.io.Serializable{
	/** 电子围栏ID */
	private long id;
	/** 经销商ID */
	private long dealerId;
	/** 仓库ID */
	private long warehouseId;
	/** 围栏类型: circle|rect|polygon */
	private String shape;
	/** 围栏坐标: circle(radius,center)|rect(upperleft,bottomright)|polygon(point list) */
	private String locationStr;
	/** 审批状态: 0--审批中; 1--通过; 2--驳回 */
	private int auditStatus;
	/** 监控状态: 0--未监控; 1--监控中 */
	private int monitoring;
	/** 最近检查时间 */
	private Date latestTrackingTime;
	/** 最近检查在围栏内车辆数 */
	private int latestVehicleCount;
}