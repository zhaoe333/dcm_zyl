package com.cm.dubbo.safebox;

import com.cm.dubbo.dealer.GeoFenceInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * SafeBox绑定是查看的经销商信息
 * @author pengtao
 * 2020-12-2
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SafeBoxDealerInfo {

    @ApiModelProperty("经销商ID")
    private long id;

    @ApiModelProperty("经销商名称")
    private String name;

    @ApiModelProperty("经销商名称")
    private String code;

    @ApiModelProperty("经销商等级")
    private String rating;

    @ApiModelProperty("所在区域")
    private String region;

    @ApiModelProperty("集团名称")
    private String group;

    @ApiModelProperty("围栏信息")
    private List<GeoFenceInfo> fences;
}
