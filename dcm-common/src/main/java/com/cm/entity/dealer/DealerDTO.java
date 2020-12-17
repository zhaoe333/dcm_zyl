package com.cm.entity.dealer;

import com.cm.entity.OperationDTO;
import lombok.Data;

/**
 * 经销商信息DTO
 * @author popo
 * 2020-11-18
 */
@Data
public class DealerDTO extends OperationDTO {
    /** 经销商ID */
    private long id = -1L;
    /** 经销商代码 */
    private String code;
    /** 经销商名称 */
    private String name;
    /** 所在区域 */
    private String region;
    /** 集团名称 */
    private String group;
    /** 经销商等级 */
    private String rating;
    /** Business partner ID */
    private String bpid;
    /** Classification: [Active, Inactive, Cancel, Watch] */
    private String status;
    /** Dealer finance manager email address */
    private String email;
    /** Guarantor Chinese name semicomma as splitter in case of multiple */
    private String guarantor;
    /** 数据来源: [CMS] */
    private String source;
}
