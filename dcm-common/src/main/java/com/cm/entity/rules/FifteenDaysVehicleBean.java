package com.cm.entity.rules;

import com.cm.entity.CommonBean;
import lombok.Data;

import java.util.Date;

/**
 * 15天未到店车辆信息 Entity Bean
 * @author popo
 * 2020-11-6
 */
@Data
public class FifteenDaysVehicleBean extends CommonBean {
    /** ID */
    private long id;
    /** 车辆VIN码 */
    private String vin;
    /** 到店日期(物流报告上传) */
    private Date arrivalDate;
    /** 处理状态: 0--Pending; 1--Arrived; 2--Violated */
    private int handlingState;
    /** 备注 */
    private String remark;
}
