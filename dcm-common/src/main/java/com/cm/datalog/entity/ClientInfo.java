package com.cm.datalog.entity;

import lombok.Data;

@Data
public class ClientInfo {

    private String clientId;      //  userId,vin etc.
    private String deviceId;    // Unique device id (if possible)
    private String deviceType;  // Type of the device, such as pc, mobile, headunit,vehicle etc.
    private String deviceSystem;    // Device system info, for example Android, iOS, vehicle tbox etc.

}
