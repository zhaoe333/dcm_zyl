package com.cm.entity.dealer;

import com.cm.entity.CommonBean;
import lombok.Data;

import java.util.Date;

/**
 * 车辆信息EntityBean
 * @author popo
 * 2020-11-5
 */
@Data
@Deprecated
public class VehicleBean extends CommonBean {
	/** 车辆ID */
	private long id;
	/** 经销商ID */
	private long dealerId;
	/** 所在仓库 */
	private String warehouseCode;
	/** 车辆VIN码 */
	private String vin;
	/** 车辆激活日期 */
	private Date activationDate;
	/** 车辆贷款状态: [Current, Sold Out, Settled, Recall Stock, Transferred] */
	private String loanStatus;
	/** Vehicle financed condition: [New, ...] */
	private String assetCondition;
	/** Title document release type: [Normal, Fast] */
	private String releaseType;
	/** 制造厂商 */
	private String manufacturer;
	/** Asset make, e.g., BMW,Mini,RR */
	private String make;
	/** 车型 */
	private String model;
	/** 车系 */
	private String brand;
	/** 颜色 */
	private String color;
	/** 价格 */
	private String financeAmount;
	/** 当前所在仓库地址 */
	private String assetLocation;
	/** 跟踪数据最近获取时间 */
	private Date latestTrackingTime;
	/** 数据来源: [CMS] */
	private String source;
	/** 最后更新位置 */
	private String latestLocationStr;
	/** 最后车辆状态 */
	private int latestVehicleState;
	/** 最后围栏id */
	private Long latestFenceId;
}