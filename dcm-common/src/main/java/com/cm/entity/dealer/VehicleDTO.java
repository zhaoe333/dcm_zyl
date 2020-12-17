package com.cm.entity.dealer;

import com.cm.entity.OperationDTO;
import lombok.Data;

import java.util.Date;

/**
 * 车辆信息DTO
 * @author popo
 * 2020-11-20
 */
@Data
public class VehicleDTO extends OperationDTO {
    /** 车辆ID */
    private long id = -1L;
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
    /** 数据来源: [CMS] */
    private String source;
    /** 最新GPS数据获取时间 */
    private Date latestTrackingTime;
    /** 最新车辆信息获取时间 */
    private Date latestQueryingTime;
    /** 最新车辆位置: [lon, lat] */
    private String latestLocationStr;
    /** 最新车辆状态: 0--正常(在圈); 1--异常(出圈); 2--无信号 */
    private int latestVehicleState = -1;
    /** 最新钥匙状态: 0--正常; 1--异常 */
    private int latestKeyState = -1;
    /** 最新文档状态: 0--正常; 1--异常 */
    private int latestDocState = -1;
    /** 所属经销商 */
    private DealerDTO dealer;
    /** 所在仓库 */
    private WarehouseDTO warehouse;
    /** 所在围栏 */
    private GeoFenceDTO fence;
}
