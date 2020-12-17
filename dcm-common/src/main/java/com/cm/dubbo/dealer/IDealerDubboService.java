package com.cm.dubbo.dealer;

import com.cm.entity.dealer.*;

import java.util.List;

/**
 * 经销商服务dubbo接口类
 */
public interface IDealerDubboService {
    /**
     * 查询所有经销商
     * @return
     */
    List<DealerDTO> queryDealers() throws Exception;

    /**
     * 批量查询经销商
     * @param dealerIds
     * @return
     */
    List<DealerDTO> queryDealers(List<Long> dealerIds) throws Exception;

    /**
     * 获取经销商
     * @param dealerId
     * @return
     */
    DealerDTO getDealer(long dealerId) throws Exception;

    /**
     * 批量查询车辆信息
     * @param vins
     * @return
     */
    List<VehicleDTO> queryVehicles(List<String> vins) throws Exception;

    /**
     * 获取经销商下的仓库
     * @param dealerId
     * @return
     */
    List<WarehouseDTO> queryWarehouse(long dealerId) throws Exception;

    /**
     * 批量查询围栏
     * @param fenceIds
     * @return
     */
    List<WarehouseDTO> queryFences(List<Long> fenceIds) throws Exception;

}
