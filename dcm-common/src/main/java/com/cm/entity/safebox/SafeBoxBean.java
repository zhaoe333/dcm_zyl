package com.cm.entity.safebox;

import lombok.Data;

import java.util.Date;

/**
 * safeBox安全柜 Entity Bean
 * @author pengtao
 * 2020-12-2
 */
@Data
public class SafeBoxBean {

    /** id;id */
    private Long id ;
    /** 保险柜code */
    private Integer safeBoxCode ;
    /** 经销商id */
    private Long dealerId ;
    /** 该经销商有效的围栏id */
    private Long fenceId ;
    /** 保险柜状态;0---out，1---int */
    private Integer safeBoxStatus ;
    /** 保险柜是否登录;0---off,1---in */
    private Integer safeBoxSign ;
    /** 保险柜门状态;0--open,1---close */
    private Integer doorStatus ;
    /** 锁的状态;0--normal，1--locked */
    private Integer lockStatus ;
    /** 晚上时间不可开;逗号分割 */
    private String lockSettingType ;
    /** 每天解绑最大次数;默认是10 */
    private Integer rebindingTime ;
    /** doc的数量;默认为0 */
    private Integer docNum ;
    /** key的数量;默认为0 */
    private Integer keyNum ;
    /** 绑定时间 */
    private String bindingTime ;
    /** 操作员Id;last_contact */
    private String operatorId ;
    /** 创建时间 */
    private Date createTime ;
    /** 更新时间;last_contact_time */
    private Date updateTime ;
    /** 删除标识;0--正常; 1--删除 */
    private Integer deleteFlag ;
}
