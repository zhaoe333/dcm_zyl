package com.cm.entity.sync.dragon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * dragon 同步过来的经销商信息
 */
@Data
public class DragonDealerInfo implements Serializable {

    /**Dealer Code*/
    @JsonProperty("DealerCode")
    private String dealerCode;
    /**Dealer Chinese Name*/
    @JsonProperty("DealerName")
    private String dealerName;
    /**Region*/
    @JsonProperty("Region")
    private String region;
    /**Credit Rating*/
    @JsonProperty("CreditRating")
    private String creditRating;
    /**Classification*/
    @JsonProperty("Status")
    private String status;
    /**Dealer finance manager email address*/
    @JsonProperty("Email")
    private String email;
    /**Guarantor Chinese name semicomma as splitter in case of multiple*/
    @JsonProperty("Guarantor")
    private String guarantor;
    /**NEW, MOD*/
    @JsonProperty("Action")
    private String action;
    /**Request Type*/
    @JsonProperty("Remark")
    private String remark;
    /**Request ID*/
    @JsonProperty("ReferenceID")
    private String referenceID;
    /**Dealer Reporting Group Name*/
    @JsonProperty("DealerGroup")
    private String dealerGroup;
}
