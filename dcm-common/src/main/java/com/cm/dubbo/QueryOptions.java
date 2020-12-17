package com.cm.dubbo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 搜索筛选条件查询结果
 * @author popo
 * 2020-11-11
 */
@Builder
@Data
public class QueryOptions {
    @ApiModelProperty("条件选项列表")
    private List<OptionItem> options;

    @Builder
    @Data
    public static class OptionItem {
        @ApiModelProperty("选项名称")
        private String label;
        @ApiModelProperty("取值列表")
        private List<ValueItem> values;
    }

    @Builder
    @Data
    public static class ValueItem {
        @ApiModelProperty("名称")
        private String label;
        @ApiModelProperty("取值")
        private int value;
    }
}
