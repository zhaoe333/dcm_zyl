package com.cm.test.service;

import com.cm.dubbo.IDubboTestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class TestService implements ITestService{

    @DubboReference
    private IDubboTestService dubboTestService;

    @Override
    public String testDubbo() {
        return dubboTestService.test();
    }
}
