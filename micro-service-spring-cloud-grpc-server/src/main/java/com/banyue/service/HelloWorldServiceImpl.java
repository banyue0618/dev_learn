package com.banyue.service;

//import com.banyue.annotation.GrpcService;
import com.banyue.grpc.helloworld.GreeterGrpc;
import com.banyue.grpc.helloworld.HelloReply;
import com.banyue.grpc.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author: zhangsp
 * @date: 2023/4/10 15:57
 * @description:
 */
@GrpcService
public class HelloWorldServiceImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        System.out.println(" HelloWorldServiceImpl 接收到的参数，name：" + request.getName());
        HelloReply response = HelloReply.newBuilder().setMessage("你好啊").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
//        super.sayHello(request, responseObserver);
    }

    @Override
    public void sayHelloAgain(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        System.out.println(" HelloWorldServiceImpl 接收到的参数，name：" + request.getName());
        HelloReply response = HelloReply.newBuilder().setMessage("你好啊，我又来了！").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
//        super.sayHelloAgain(request, responseObserver);
    }
}
