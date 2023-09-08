package com.banyue.grpc.interceptor;

import io.grpc.*;
import net.devh.boot.grpc.common.security.SecurityConstants;

/**
 * @author: zhangsp
 * @date: 2023/4/28 10:24
 * @description:
 */
public class AuthInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        // 提取CallCredentials
//        String authHeader = headers.get(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER));
        String authHeader = headers.get(SecurityConstants.AUTHORIZATION_HEADER);
        if (authHeader == null) {
            call.close(Status.UNAUTHENTICATED.withDescription("Authorization header not found"), headers);
            return new ServerCall.Listener<ReqT>() {};
        }

        // 验证CallCredentials
        if (!verify(authHeader)) {
            call.close(Status.UNAUTHENTICATED.withDescription("Invalid credentials"), headers);
            return new ServerCall.Listener<ReqT>() {};
        }

        // 调用下一个拦截器
        return next.startCall(call, headers);
    }


    /**
     * 验证CallCredentials
     */
    private boolean verify(String authHeader) {
        // 在这里编写身份验证逻辑
        return true;
    }
}
