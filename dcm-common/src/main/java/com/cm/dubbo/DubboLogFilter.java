package com.cm.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.Arrays;

/**
 * dubbo 日志拦截器
 */
@Activate
@Slf4j
public class DubboLogFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info(Arrays.toString(invocation.getArguments()));
        log.info(invocation.getMethodName());
        System.out.println(123123123);
        return invoker.invoke(invocation);
    }

}
