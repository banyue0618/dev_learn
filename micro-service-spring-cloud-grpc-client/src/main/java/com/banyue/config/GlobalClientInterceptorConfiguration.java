package com.banyue.config;

import com.banyue.interceptor.LogGrpcInterceptor;
import com.banyue.interceptor.RequestHeadInterceptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author: zhangsp
 * @date: 2023/4/19 16:21
 * @description:
 */
@Order
@Configuration(proxyBeanMethods = false)
public class GlobalClientInterceptorConfiguration {

    @GrpcGlobalClientInterceptor
    LogGrpcInterceptor logClientInterceptor() {
        return new LogGrpcInterceptor();
    }

    @GrpcGlobalClientInterceptor
    RequestHeadInterceptor requestHeadInterceptor() {
        return new RequestHeadInterceptor();
    }

}
