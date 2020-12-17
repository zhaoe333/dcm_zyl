package com.cm.entity.safebox;

import lombok.Data;

import java.util.Date;

/**
 * doc&key历史记录 Entity Bean
 * @author pengtao
 * 2020-12-2
 */
@Data
public class DocKeyTrackingBean {

    /** id;id */
    private Long id ;
    /** doc的rfid */
    private Long docRfid ;
    /** doc状态;0--out; 1--in; */
    private Integer docStatus ;
    /** doc保险柜code */
    private Integer docSafeBoxCode ;
    /** key的id */
    private Long keyId ;
    /** key状态;0--out; 1--in; */
    private Integer keyStatus ;
    /** key保险柜code */
    private Integer keySafeBoxCode ;
    /** 操作员Id;last_contact */
    private String operatorId ;
    /** 创建时间 */
    private Date createTime ;
    /** 更新时间;last_contact_time */
    private Date updateTime ;
    /** 删除标识;0--正常; 1--删除 */
    private Integer deleteFlag ;
}
