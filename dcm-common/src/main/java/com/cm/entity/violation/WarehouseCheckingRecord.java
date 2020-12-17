package com.cm.entity.violation;

import lombok.Data;

import java.util.Date;

/**
 * 仓库检查记录Entity
 * @author popo
 * @createTime 2020-9-25
 */
@Data
@Deprecated
public class WarehouseCheckingRecord  implements java.io.Serializable {
    private long id;
    private String warehouseId;
    private int vehicleCounts;
    private Date checkingTime;
}
