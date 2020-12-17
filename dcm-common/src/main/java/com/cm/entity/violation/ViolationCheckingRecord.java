package com.cm.entity.violation;

import lombok.Data;

import java.util.Date;

/**
 * 违规检查记录Entity
 * @author popo
 * @createTime 2020-9-25
 */
@Data
@Deprecated
public class ViolationCheckingRecord  implements java.io.Serializable {
    private Date id;
    private String dealerId;
    private String checkBy;
}
