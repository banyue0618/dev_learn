package com.banyue.config;

import com.banyue.grpc.helloworld.GreeterGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.StubTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangsp
 * @date: 2023/4/10 16:39
 * @description:
 */
@Configuration
public class GrpcServiceConfig {

//    @Bean
//    public ManagedChannel getChannel() {
//        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9091)
//                .usePlaintext()
//                .build();
//        return channel;
//    }
//    @Bean
//    public GreeterGrpc.GreeterBlockingStub getStub1(ManagedChannel channel) {
//        return GreeterGrpc.newBlockingStub(channel);
//    }


//    @Bean
//    public StubTransformer call() {
//        return (name, stub) -> {
//            if ("deptServiceStub".equals(name)) {
//                System.out.println("-------------------->");
//                return stub.withWaitForReady();
//            } else {
//                return stub;
//            }
//        };
//    }
}
