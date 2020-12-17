package com.cm.entity.rules;

import com.cm.entity.CommonBean;
import lombok.Data;

import java.util.Date;

/**
 * 无信号车辆信息 Entity Bean
 * @author popo
 * 2020-11-6
 */
@Data
public class NoSingalVehicleBean extends CommonBean {
    /** ID */
    private long id;
    /** 车辆VIN码 */
    private String vin;
    /** T日 */
    private int tDays;
    /** 照片上传时间 */
    private Date uploadingTime;
    /** 照片路径: url1;url2 */
    private String uploadingPhoto;
    /** 处理状态: 0--Pending; 1--Confirmed; 2--Violation; 3--Rejected */
    private int handlingState;
}
