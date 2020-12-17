package com.cm.test.dubbo;

import com.cm.dubbo.IDubboTestService;
import org.apache.dubbo.config.annotation.DubboService;

import java.net.InetAddress;
import java.net.UnknownHostException;

@DubboService
public class DubboTestService implements IDubboTestService {


    @Override
    public String test() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "unknown host";
    }
}
