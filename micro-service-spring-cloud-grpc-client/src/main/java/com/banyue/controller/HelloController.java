package com.banyue.controller;

import com.banyue.grpc.dept.*;
import com.banyue.grpc.helloworld.GreeterGrpc;
import com.banyue.grpc.helloworld.HelloReply;
import com.banyue.grpc.helloworld.HelloRequest;
import io.grpc.CallCredentials;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author: zhangsp
 * @date: 2023/4/10 16:50
 * @description:
 */
@RestController
public class HelloController {

//    @GrpcClient("micro-service-grpc-server")
    private GreeterGrpc.GreeterBlockingStub greeterBlockingStub;

    @GrpcClient("micro-service-provider-dept")
    private DeptServiceGrpc.DeptServiceBlockingStub deptServiceBlockingStub;

    @GrpcClient("micro-service-provider-dept")
    DeptServiceGrpc.DeptServiceStub deptServiceStub;

    @Qualifier("basicAuthCredentials")
    @Autowired
    private CallCredentials basicAuthCredentials;

    @GetMapping("/hello")
    public String hello(){
        HelloRequest hello = HelloRequest.newBuilder().build();

        HelloReply reply = greeterBlockingStub.sayHello(hello);

        return reply.getMessage();
    }

    @GetMapping("/helloAgain")
    public String helloAgain(){
        HelloRequest hello = HelloRequest.newBuilder().build();

        HelloReply reply = greeterBlockingStub.sayHelloAgain(hello);

        return reply.getMessage();
    }

    @GetMapping("/queryDeptList")
    public DeptResponse queryDeptList() {
        DeptRequest queryBean = DeptRequest.newBuilder().setName("12").build();

        deptServiceBlockingStub.withCallCredentials(basicAuthCredentials);

        DeptResponse response = deptServiceBlockingStub.queryDeptList(queryBean);

        return response;
    }


    @GetMapping("/sendAndReply")
    public DeptResponse sendAndReply() {

        List<DeptRequest> requests = new ArrayList<>();
        requests.add(DeptRequest.newBuilder().setName("12").build());
        requests.add(DeptRequest.newBuilder().setName("13").build());
        requests.add(DeptRequest.newBuilder().setName("14").build());
        requests.add(DeptRequest.newBuilder().setName("15").build());
        requests.add(DeptRequest.newBuilder().setName("16").build());

        CountDownLatch latch = new CountDownLatch(1);

        deptServiceStub.withCallCredentials(basicAuthCredentials);

        StreamObserver<DeptRequest> sendAndReply = deptServiceStub.sendAndReply(new StreamObserver<DeptResponse>() {
            @Override
            public void onNext(DeptResponse value) {
                System.out.println("response:" + value.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
                latch.countDown();
            }
        });
        for (DeptRequest request : requests) {
            sendAndReply.onNext(request);
        }
        sendAndReply.onCompleted();
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        DeptResponse response = DeptResponse.newBuilder().setMessage("success").build();

        return response;
    }

}
