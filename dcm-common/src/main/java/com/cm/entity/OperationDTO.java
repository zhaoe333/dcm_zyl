package com.cm.entity;

import lombok.Data;

import java.util.Date;

/**
 * 运维信息DTO
 * @author popo
 * 2020-11-18
 */
@Data
public class OperationDTO {
    /** 操作员ID */
    private String operatorId;
    /** 创建时间 */
    private Date createTime;
    /** 最后更新时间 */
    private Date updateTime;
    /** 删除标识: 0--正常; 1--删除 */
    private int deleteFlag;
}
