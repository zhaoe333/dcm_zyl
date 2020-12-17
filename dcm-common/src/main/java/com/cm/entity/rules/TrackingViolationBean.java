package com.cm.entity.rules;

import com.cm.entity.CommonBean;
import lombok.Data;

/**
 * 车辆跟踪(GPS)违规信息 Entity Bean
 * @author popo
 * 2020-11-6
 */
@Data
public class TrackingViolationBean extends CommonBean {
    /** ID */
    private long id;
    /** 车辆VIN码 */
    private String vin;
    /** 违规类型: 0--异常(出圈) */
    private int violationType;
    /** 处理状态: 0--Pending; 1--Processed */
    private int handlingState;
}
