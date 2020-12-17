package com.cm.dubbo.safebox;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * DocKey规则查询结果
 * @author pengtao
 * 2020-12-2
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocKeyInfo {

    @ApiModelProperty("DocRFID;doc的id")
    private Long docRfid ;

    @ApiModelProperty("docFile;doc&key的id，以这个id参数调用查看图片接口,doc文件访问路径，若上传失败则为空")
    private String[] docFile ;

    @ApiModelProperty("docStatus;0--out; 1--in;")
    private Integer docStatus;
    @ApiModelProperty("keyRFID;key的id")

    private Long keyRfid ;
    @ApiModelProperty("key_status;0--out; 1--in;")

    private Integer keyStatus ;
    @ApiModelProperty("vin;vin")

    private String vin ;
    @ApiModelProperty("last_contact;最后操作人")

    private String lastContact ;

    @ApiModelProperty("last_contact_time;最后操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastContactTime ;

    @ApiModelProperty("asset_condition;车辆类型，新车，二手车等，cms传过来的")
    private String assetCondition ;

    @ApiModelProperty("经销商code")
    private Long dealerCode;

    @ApiModelProperty("经销商名称")
    private String dealerName;

    @ApiModelProperty("经销商等级")
    private Integer CRS;

    @ApiModelProperty("经销商组")
    private String group;

    @ApiModelProperty("经销商区域")
    private String region;

    @ApiModelProperty("文件的utl路径,没有图片的话返回''")
    private String fileUrl ;
}
