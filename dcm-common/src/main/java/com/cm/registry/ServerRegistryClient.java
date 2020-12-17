package com.cm.registry;

import com.cm.common.utils.LogicUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.net.InetAddress;

/**
 * 注册服务到zk
 * TODO 初版 简单实现 后续再扩展
 * TODO 后续需要支持多种数据中心
 */
@Data
@Slf4j
public class ServerRegistryClient {

    @Value("${server.servlet.context-path:}")
    private String context;

    @Value("${server.port:80}")
    private int port;

    @Value("${dubbo.registry.address:}")
    private String registryAddress;
    /**
     * weight为0会导致轮训失败
     */
    @Value("${server.registry.weight:1}")
    private int weight;

    @Value("${server.registry.enable:false}")
    private boolean enable;

    @Value("${server.registry.namespace:dcm}")
    private String namespace;

    @Value("${server.registry.session-timeout:30000}")
    private int sessionTimeout;

    @Value("${server.registry.connect-timeout:5000}")
    private int connectTimeout;

    @Value("${server.registry.retry:5}")
    private int retry;

    @Value("${server.registry.retry-period:1000}")
    private int retryPeriod;

    /**
     * zk客户端
     */
    private CuratorFramework client;

    public ServerRegistryClient(){

    }

    @PostConstruct
    public void init(){
        //TODO 链接zk并注册服务
        if(!enable){
            log.info("server registry is disable!");
            return;
        }
        if(LogicUtil.isNullOrEmpty(context)||LogicUtil.isNullOrEmpty(registryAddress)){
            log.info("registry failed by missing params!");
            return;
        }
        try{
            //初始化zookeeper链接
            this.client = CuratorFrameworkFactory.builder().connectString(registryAddress.replace("zookeeper://",""))
                    .connectionTimeoutMs(connectTimeout).sessionTimeoutMs(sessionTimeout)
                    .retryPolicy(new ExponentialBackoffRetry(retryPeriod, retry))
                    .namespace(namespace).build();
            this.client.start();
            //创建父节点
            if(null == this.client.checkExists().forPath(context)){
                this.client.create().withMode(CreateMode.PERSISTENT).forPath(context,"".getBytes());
            }
            //创建服务节点
            String host = InetAddress.getLocalHost().getHostAddress();
            String data = host + "," + port + "," + weight;
            this.client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(context+"/node-", data.getBytes());
            log.info("success registry server " + context);
        }catch(Exception e){
            log.error("registry failed by exception", e);
            e.printStackTrace();
        }

    }

}
