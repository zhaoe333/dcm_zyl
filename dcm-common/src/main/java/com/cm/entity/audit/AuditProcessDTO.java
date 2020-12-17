package com.cm.entity.audit;

import com.cm.entity.OperationDTO;
import com.cm.entity.dealer.DealerDTO;
import lombok.Data;

/**
 * 审批记录信息DTO
 * @author popo
 * 2020-11-30
 */
@Data
public class AuditProcessDTO extends OperationDTO {
    /** ID */
    private long id = -1L;
    /** 审批任务类别: [0--围栏审批; 1--删除仓库审批; 2--经销商GPS规则审批; 3--仓库围栏审批; 4--GPS规则审批; 5--长距离迁移审批] */
    private int auditType = -1;
    /** 审批状态: [0--Pending; 1--Approved; 2--Declined; 3--Rejected] */
    private int auditStatus = -1;
    /** 申请人 */
    private String applicant;
    /** 审批人 */
    private String approver;
    /** Activiti ProcessInstance ID */
    private String actInstanceId;
    /** 结束标记: 0--未结束; 1--已结束 */
    private int actFinishFlag = -1;
    /** 经销商信息 */
    private DealerDTO dealer;
}
