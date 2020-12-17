package com.cm.dubbo.violation;

import java.util.List;
import java.util.Map;

/**
 * 违规服务dubbo接口类
 * @author popo
 */
public interface IRulesDubboService {

    /**
     * 查询经销商GPS规则信息
     */
    List<DealerTrackingRulesInfo> queryDealerRules(List<Long> dealerIds);

    /**
     * 修改GPS规则
     */
    void updateRules(Map<String, Object> variables);

    /**
     * 首次保存经销商和GPS规则关系
     */
    void saveFirstDealerAndTracking(Map<String, Object> variables);

}
