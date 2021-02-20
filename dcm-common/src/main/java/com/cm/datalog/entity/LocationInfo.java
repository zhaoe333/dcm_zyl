package com.cm.datalog.entity;

import lombok.Data;

@Data
public class LocationInfo {

    private Double latitude;
    private Double longitude;
    private String coordinateType;  // "Baidu", "GCJ-02" etc.
//    private String country;
//    private String province;
//    private String city;
//    private String district;
//    private String street;
//    private Object extra;

    public LocationInfo() {
    }

    public LocationInfo(double latitude, double longitude, String coordinateType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.coordinateType = coordinateType;
    }

    /**
     * 默认 "GCJ-02" 火星坐标
     * @param latitude
     * @param longitude
     */
    public LocationInfo(double latitude, double longitude) {
        this(latitude, longitude, "GCJ-02");
    }

}
