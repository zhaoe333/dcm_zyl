package com.cm.entity.sync.dragon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * dragon同步过来的仓库信息
 */
@Data
public class DragonAssetLocationInfo implements Serializable {

    /**Dealer Code*/
    @JsonProperty("DealerCode")
    private String dealerCode;
    /**Dealer Chinese name*/
    @JsonProperty("DealerName")
    private String dealerName;
    @Deprecated
    @JsonProperty("DealerLocation")
    private String dealerLocation;
    /**Dealer Location Code*/
    @JsonProperty("LocationCode")
    private String locationCode;
    /**Location Type*/
    @JsonProperty("LocationType")
    private String locationType;
    /**Asset Location Address*/
    @JsonProperty("AddressDetail")
    private String addressDetail;
    /**Location BP status*/
    @JsonProperty("Classification")
    private String classification;
    /**Asset Location Effective Date*/
    @JsonProperty("EffectiveDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date effectiveDate;
    /**Asset Location Expiry Date*/
    @JsonProperty("ExpiryDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expiryDate;
    /**Property Type set in Location BP*/
    @JsonProperty("PropertyType")
    private String propertyType;
    /**Active or inactive*/
    @JsonProperty("LocationStatus")
    private String locationStatus;
    /**NEW,LOCMOD,MOD,DEL*/
    @JsonProperty("Action")
    private String action;
    /**Request Type*/
    @JsonProperty("Remark")
    private String remark;
    /**Request ID*/
    @JsonProperty("ReferenceID")
    private String referenceID;
    /**Location creation request status,*/
    @JsonProperty("ApproveStatus")
    private String approveStatus;
}
