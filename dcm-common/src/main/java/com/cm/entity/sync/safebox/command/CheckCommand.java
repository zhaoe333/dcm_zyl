package com.cm.entity.sync.safebox.command;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 手动盘点指令
 */
@Data
public class CheckCommand implements Serializable {
    /**
     * 指定盘点哪个门 为null或者size为0时 开所有门
     */
    private List<Integer> doorNumberList;
}
