package com.cm.entity.dealer;

import com.cm.entity.CommonBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 车辆跟踪信息(历史记录)EntityBean
 * @author popo
 * 2020-11-5
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTrackingBean extends CommonBean {
    /** ID */
    private long id;
    /** VIN */
    private String vin;
    /** 所在仓库 */
    private String warehouseCode;
    /** 坐标纬度 */
    private String latitude;
    /** 坐标经度 */
    private String longitude;
    /** 车辆状态: 0--正常(在圈); 1--异常(出圈); 2--无信号 */
    private int checkingState;
    /** 首次查询标记: 0--false; 1--true */
    private int firstFlag;
    /** fence*/
    private Long fenceId;
}
