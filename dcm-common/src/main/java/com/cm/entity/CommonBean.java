package com.cm.entity;

import lombok.Data;

import java.util.Date;

/**
 * Common Entity Bean
 * @author popo
 * 2020-11-5
 */
@Data
public class CommonBean {
    /** 操作员ID */
    private String operatorId;
    /** 创建时间 */
    private Date createTime;
    /** 最后更新时间 */
    private Date updateTime;
    /** 删除标识: 0--正常; 1--删除 */
    private int deleteFlag;
}
