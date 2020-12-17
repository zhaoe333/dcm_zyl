package com.cm.dubbo.sync.safebox;


import com.cm.entity.sync.safebox.SafeBoxEvent;
import com.cm.entity.sync.safebox.up.*;

public interface ISafeBoxEventService {
    /**
     * 上线数据处理
     * safebox 链接网络后会首先发送上线数据
     * @param event
     */
    void onlineHandle(SafeBoxEvent<OnlineInfo> event);

    /**
     * 离线事件处理
     * safebox断开网络后会触发该事件
     * @param event
     */
    void offlineHandle(SafeBoxEvent<OfflineInfo> event);

    /**
     * 盘点数据处理
     * 每次关门盘点和下发指令盘点后会触发该事件
     * 如果是开门之后关门盘点，msgId为开门指令的msgId
     * 如果为下发盘点指令触发的盘点，msgId为盘点指令的msgId
     * 如果msgId为null 则有可能是保险柜被物理打开导致的
     * @param event
     */
    void checkHandle(SafeBoxEvent<CheckInfo> event);

    /**
     * 命令响应数据处理
     * @param event
     */
    void responseHandle(SafeBoxEvent<ResponseInfo> event);

    /**
     * 报警信息处理
     * 蜂鸣器和邮件报警
     * @param event
     */
    void warnHandle(SafeBoxEvent<WarnInfo> event);

    /**
     * 开门关门均会触发该事件
     * @param event
     */
    void doorLockHandle(SafeBoxEvent<DoorLockInfo> event);

    /**
     * 半小时上传一次 注意这里是大地坐标系 请在业务层将其转成高德的火星坐标系
     * @param event
     */
    void gpsHandle(SafeBoxEvent<GpsInfo> event);

}
