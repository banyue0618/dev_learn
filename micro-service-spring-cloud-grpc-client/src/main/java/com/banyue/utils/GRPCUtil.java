package com.banyue.utils;

import io.grpc.Metadata;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.MetadataUtils;

import java.util.Map;

/**
 * @author: zhangsp
 * @date: 2023/5/8 11:16
 * @description:
 */
public class GRPCUtil {

    private static <T extends AbstractStub<T>> T attachHeaders(T stub, final Map<String, String> headerMap) {
        Metadata extraHeaders = new Metadata();
        if (headerMap != null) {
            for (String key : headerMap.keySet()) {
                Metadata.Key<String> customHeadKey = getMetadataStringKey(key);
                extraHeaders.put(customHeadKey, headerMap.get(key));
            }
        }
        return MetadataUtils.attachHeaders(stub, extraHeaders);
    }

    public static Metadata.Key<String> getMetadataStringKey(String keyName){
        return Metadata.Key.of(keyName, Metadata.ASCII_STRING_MARSHALLER);
    }

    public static Metadata.Key<byte[]> getMetadataBinaryKey(String keyName){
        return Metadata.Key.of(keyName, Metadata.BINARY_BYTE_MARSHALLER);
    }

}
