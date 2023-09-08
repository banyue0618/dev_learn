package com.banyue.interceptor;

import com.banyue.utils.GRPCUtil;
import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zhangsp
 * @date: 2023/5/8 11:11
 * @description: 请求头设置拦截器
 */
public class RequestHeadInterceptor implements ClientInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestHeadInterceptor.class);

    /**
     * 客户端header的key
     */
    static final Metadata.Key<String> TOKEN = GRPCUtil.getMetadataStringKey("token");
    /**
     * 客户端header的key
     */
    static final Metadata.Key<String> APPID = GRPCUtil.getMetadataStringKey("appId");

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                //此处传入客户端的token
                headers.put(TOKEN, "");
                headers.put(APPID, "");
                super.start(responseListener, headers);
            }
        };
    }
}
