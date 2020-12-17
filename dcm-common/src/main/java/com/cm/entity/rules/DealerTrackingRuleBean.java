package com.cm.entity.rules;

import com.cm.entity.CommonBean;
import lombok.Data;

/**
 * 经销商GPS规则信息 Entity Bean
 * @author popo
 * 2020-11-6
 */
@Data
public class DealerTrackingRuleBean extends CommonBean {
    /** ID */
    private long id;
    /** 经销商ID */
    private long dealerId;
    /** 规则ID */
    private long ruleId;
}
