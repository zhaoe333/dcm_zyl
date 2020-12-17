package com.cm.entity.violation;

import lombok.Data;

import java.util.Date;

/**
 * 车辆违规记录Entity
 * @author popo
 * @createTime 2020-9-25
 */
@Data
@Deprecated
public class VehicleCheckingRecord  implements java.io.Serializable {
    private long id;
    private String vin;
    private String dealerId;
    private String location;
    private Date checkingTime;
    private Date gpsTime;
}
