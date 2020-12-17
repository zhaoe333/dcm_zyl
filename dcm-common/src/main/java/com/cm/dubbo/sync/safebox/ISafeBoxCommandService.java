package com.cm.dubbo.sync.safebox;

import java.util.List;

/**
 * safebox 指令发送接口
 * TODO 注意里面的批量方法 均为单线程阻塞执行 如果考虑多线程 则需要重构
 */
public interface ISafeBoxCommandService {
    /**
     * 开门指令
     * @param safeBoxCode safeboxcode
     * @param beepTime 蜂鸣器响的持续时间 ms
     * @param alertTime 指定蜂鸣器报警的时间 ms
     * @param mailTime 指定发送邮件的时间 ms
     * @param doorNumberList 指定开哪个门 为null或者size为0时 开所有门 可以指定开1,2,3,4
     * @return msgId
     */
    String open(String safeBoxCode, int beepTime, int alertTime, int mailTime, List<Integer> doorNumberList);

    /**
     * 批量开门指令
     * @param safeBoxCodes safeboxcodes
     * @param beepTime 蜂鸣器响的持续时间 ms
     * @param alertTime 指定蜂鸣器报警的时间 ms
     * @param mailTime 指定发送邮件的时间 ms
     * @param doorNumberList 指定开哪个门 为null或者size为0时 开所有门 可以指定开1,2,3,4
     * @return msgId 顺序和list内的顺序相同
     */
    List<String> openBatch(List<String> safeBoxCodes, int beepTime, int alertTime, int mailTime, List<Integer> doorNumberList);

    /**
     * 盘点指令
     * @param safeBoxCode safeboxcode
     * @param doorNumberList 指定盘点哪个门 为null或者size为0时 开所有门 可以指定开1,2,3,4
     * @return msgId
     */
    String check(String safeBoxCode, List<Integer> doorNumberList);

    /**
     * 盘点指令
     * @param safeBoxCodes safeboxcodes
     * @param doorNumberList 指定盘点哪个门 为null或者size为0时 开所有门 可以指定开1,2,3,4;
     * @return msgId 顺序和list内的顺序相同
     */
    List<String> checkBatch(List<String> safeBoxCodes, List<Integer> doorNumberList);

    /**
     * 蜂鸣器鸣响指令
     * @param safeBoxCode safeboxcode
     * @param beepTime 鸣响持续时间
     * @return msgId
     */
    String warn(String safeBoxCode, int beepTime);

    /**
     * 蜂鸣器鸣响指令
     * @param safeBoxCodes safeboxcode
     * @param beepTime 鸣响持续时间
     * @return msgId 顺序和list内的顺序相同
     */
    List<String> warnBatch(List<String> safeBoxCodes, int beepTime);

    /**
     * ota升级指令
     * @param safeBoxCode safeboxcode
     * @param apkVersionCode 版本号
     * @param apkFullUrl 软件升级路径
     * @return msgId
     */
    String ota(String safeBoxCode, int apkVersionCode, String apkFullUrl);

    /**
     * ota升级指令
     * @param safeBoxCodes safeboxcode
     * @param apkVersionCode 版本号
     * @param apkFullUrl 软件升级路径
     * @return msgId 顺序和list内的顺序相同
     */
    List<String> otaBatch(List<String> safeBoxCodes, int apkVersionCode, String apkFullUrl);
}
