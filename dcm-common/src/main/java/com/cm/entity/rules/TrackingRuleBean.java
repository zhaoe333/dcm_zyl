package com.cm.entity.rules;

import com.cm.entity.CommonBean;
import lombok.Data;

/**
 * GPS规则信息 Entity Bean
 * @author popo
 * 2020-11-6
 */
@Data
public class TrackingRuleBean extends CommonBean {
    /** ID */
    private long id;
    /** 规则名称 */
    private String name;
    /** 规则类别: 0--固定时间; 1--间隔时间 */
    private int executionType;
    /** 规则执行方式: type=0为整点执行时间,多个值时逗号分隔.如10,22; type1为间隔分钟(自整点开始):触发次数,如10:1/20:2/30:3/60:4 */
    private String executionTime;
}
