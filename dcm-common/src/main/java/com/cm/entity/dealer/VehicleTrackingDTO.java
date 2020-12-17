package com.cm.entity.dealer;

import com.cm.entity.OperationDTO;
import lombok.Data;

/**
 * 车辆跟踪信息(历史记录)DTO
 * @author popo
 * 2020-11-20
 */
@Data
public class VehicleTrackingDTO extends OperationDTO {
    /** ID */
    private long id = -1L;
    /** VIN */
    private String vin;
    /** 坐标纬度 */
    private String latitude;
    /** 坐标经度 */
    private String longitude;
    /** 车辆状态: 0--正常(在圈); 1--异常(出圈); 2--无信号 */
    private int checkingState = -1;
    /** 首次查询标记: 0--false; 1--true */
    private int firstFlag = -1;
    /** 所属经销商 */
    private DealerDTO dealer;
    /** 所在仓库 */
    private WarehouseDTO warehouse;
    /** 所在围栏 */
    private GeoFenceDTO fence;
}
