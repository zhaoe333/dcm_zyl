package com.cm.entity.dealer;

import com.cm.entity.OperationDTO;
import lombok.Data;

import java.util.Date;

/**
 * 经销商仓库围栏信息(数据层)
 * @author popo
 * 2020-11-18
 */
@Data
public class WarehouseDTO extends OperationDTO {
    /** 仓库ID */
    private long id = -1L;
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
    /** 经销商信息 */
    private DealerDTO dealer;
    /** 电子围栏信息 */
    private GeoFenceDTO fence;
}
