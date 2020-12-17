package com.cm.entity.safebox;

import lombok.Data;

import java.util.Date;

/**
 * doc&key Entity Bean
 * @author pengtao
 * 2020-12-2
 */
@Data
public class DocKeyBean {

    /** id */
    private Long id ;
    /** doc保险柜code */
    private Integer docSafeBoxCode ;
    /** doc的rfid;doc的id */
    private Long docRfid ;
    /** doc状态;0--out; 1--in; */
    private Integer docStatus ;
    /** doc文件访问路径;若上传失败则为空 */
    private String docFile ;
    /** doc在哪个门里面 */
    private String docDoor ;
    /** key的rfid;key的id */
    private Long keyRfid ;
    /** key的状态;0--out; 1--in; */
    private Integer keyStatus ;
    /** key保险柜code */
    private Integer keySafeBoxCode ;
    /** key在哪个门里面 */
    private String keyDoor ;
    /** vin;vin */
    private String vin ;
    /** 操作员Id;last_contact */
    private String operatorId ;
    /** 创建时间 */
    private Date createTime ;
    /** 更新时间;last_contact_time */
    private Date updateTime ;
    /** 删除标识;0--正常; 1--删除 */
    private Integer deleteFlag ;
}
