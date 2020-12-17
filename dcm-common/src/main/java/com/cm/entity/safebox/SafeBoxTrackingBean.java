package com.cm.entity.safebox;

import lombok.Data;

import java.util.Date;

/**
 * safeBox安全柜历史数据 Entity Bean
 * @author pengtao
 * 2020-12-2
 */
@Data
public class SafeBoxTrackingBean {

    /** id;id */
    private Long id ;
    /** 保险柜code */
    private String safeCode ;
    /** 保险柜状态;0---out，1---int */
    private Integer safeBoxStatus ;
    /** 保险柜门状态;0--open,1---close */
    private Integer doorStatus ;
    /** 操作员Id;last_contact */
    private String operatorId ;
    /** 创建时间 */
    private Date createTime ;
    /** 更新时间;last_contact_time */
    private Date updateTime ;
    /** 删除标识;0--正常; 1--删除 */
    private Integer deleteFlag ;
}
