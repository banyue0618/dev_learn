package com.banyue.grpc.interceptor;

import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangsp
 * @date: 2023/4/19 16:19
 * @description:
 */
@Configuration(proxyBeanMethods = false)
public class GlobalInterceptorConfiguration {

    @GrpcGlobalServerInterceptor
    RequestInterceptor requestInterceptor() {
        return new RequestInterceptor();
    }

    @GrpcGlobalServerInterceptor
    AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

}
