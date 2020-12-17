package com.cm.dubbo.audit;

import com.cm.common.exception.FMException;

import java.util.Map;

/**
 * 审批dubbo service接口类
 * @author popo
 */
public interface IAuditProcessDubboService {
    /**
     * GPS规则设置审批
     */
    void startGPSRuleProcess(Map<String, Object> variables) throws FMException;
    /**
     * 经销商GPS规则设置审批
     */
    void startDealerGPSRuleProcess(Map<String, Object> variables) throws FMException;
    /**
     * 长距离迁移审批
     */
    void startLongDistanceProcess(Map<String, Object> variables) throws FMException;
}
