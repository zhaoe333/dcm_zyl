package com.cm.entity.sync.dragon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 盘库计划
 */
@Data
public class DragonAuditPlan implements Serializable {
    /**Audit Type*/
    @JsonProperty("AuditType")
    private String auditType;
    /**Scheduled Date*/
    @JsonProperty("AuditDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date auditDate;
    /**Dealer Code*/
    @JsonProperty("DealerCode")
    private String dealerCode;
    /**Dealer  Name*/
    @JsonProperty("DealerName")
    private String dealerName;
    /**NEW, MOD, DEL*/
    @JsonProperty("Action")
    private String action;
    /***/
    @JsonProperty("AuditorInfo")
    private List<Auditor> auditorInfo;

    /**Request Type*/
    @JsonProperty("Remark")
    private String remark;
    /**Request ID*/
    @JsonProperty("ReferenceID")
    private String referenceID;
}

@Data
class Auditor implements Serializable {
    /**Auditor Buisness Partner ID*/
    @JsonProperty("AuditorCode")
    private String auditorCode;
    /**Auditor User ID*/
    @JsonProperty("AuditorId")
    private String auditorId;
    /**Auditor Name shown in the audit schedule screen*/
    @JsonProperty("AuditorName")
    private String auditorName;
}
