package com.cm.entity.sync.dragon;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * dragon 同步过来的车辆信息
 */
@Data
public class DragonVehicleInfo implements Serializable {
    /**NSC Dealer Code*/
    @JsonProperty("DealerCode")
    private String dealerCode;
    /**Dealer Chinese Name*/
    @JsonProperty("DealerName")
    private String dealerName;
    /**Loan referrence number*/
    @JsonProperty("LoanReferNum")
    private String loanReferNum;
    /**The date loan get activated.*/
    @JsonProperty("ActivationDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date activationDate;
    /**The date loan expires.*/
    @JsonProperty("MaturityDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date maturityDate;
    /**Description of loan finance type.*/
    @JsonProperty("FinanceType")
    private String financeType;
    /**Wholesale finance provider*/
    @JsonProperty("Portfolio")
    private String portfolio;
    /**Finance amount*/
    @JsonProperty("FinanceAmount")
    private String financeAmount;
    /**Outstanding amount*/
    @JsonProperty("LoanOutstanding")
    private Integer loanOutstanding;
    /**Vehicle manufacturer (Manafacturer  Local Name)*/
    @JsonProperty("Manufacturer")
    private String manufacturer;
    /**VIN Number*/
    @JsonProperty("VINNumber")
    private String vin;
    /**Asset Make*/
    @JsonProperty("AssetMake")
    private String assetMake;
    /**Vehicle model*/
    @JsonProperty("AssetModel")
    private String assetModel;
    /**Vehicle brand*/
    @JsonProperty("AssetBrand")
    private String assetBrand;
    /**Model Code*/
    @JsonProperty("AssetCode")
    private String assetCode;
    /**Vehicle financed condition*/
    @JsonProperty("AssetCondition")
    private String assetCondition;
    /**Asset color code*/
    @JsonProperty("AssetColor")
    private String assetColor;
    /**Loan status*/
    @JsonProperty("LoanStatus")
    private String loanStatus;
    /**Current asset located location code*/
    @JsonProperty("LocationCode")
    private String locationCode;
    /**Loan additional finance status*/
    @JsonProperty("AdditionalStatus")
    private String additionalStatus;
    /**Date dealer raise repayment request*/
    @JsonProperty("SoldDateTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date soldDateTime;
    /**Title Document Release Type*/
    @JsonProperty("ReleaseType")
    private String releaseType;
    /**Date loan's principle got complete repaid*/
    @JsonProperty("SettledDateTime     ")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date settledDateTime;
    /**NEW,MOD*/
    @JsonProperty("Action")
    private String action;
    /**Request Type*/
    @JsonProperty("Remark")
    private String remark;
    /**Request ID*/
    @JsonProperty("ReferenceID")
    private String referenceID;
}
