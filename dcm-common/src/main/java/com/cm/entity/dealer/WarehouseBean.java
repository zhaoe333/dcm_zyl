package com.cm.entity.dealer;

import com.cm.entity.CommonBean;
import lombok.Data;

import java.util.Date;

/**
 * 仓库信息EntityBean
 * @author popo
 * 2020-11-5
 */
@Data
@Deprecated
public class WarehouseBean extends CommonBean {
	/** 仓库ID */
	private long id;
	/** 经销商ID */
	private long dealerId;
	/** 仓库代码 */
	private String locationCode;
	/** 仓库类型: [4S, 4S-1, Showroom, Cross Location, Exhibition] */
	private String locationType;
	/** 仓库地址 */
	private String address;
	/** 仓库地址坐标: [lon, lat] */
	private String locationStr;
	/** 仓库生效日期 */
	private Date effectiveDate;
	/** 仓库到期日期 */
	private Date expiryDate;
	/** 资产类别: [Land Use Right, Lease Agreement] */
	private String propertyType;
	/** Active or inactive(功能待确认) */
	private String locationStatus;
	/** 审批状态(待确认) */
	private String auditStatus;
	/** 数据来源: [DCM, CMS] */
	private String source;
}