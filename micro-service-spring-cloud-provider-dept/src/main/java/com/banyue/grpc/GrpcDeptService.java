package com.banyue.grpc;

import com.banyue.grpc.dept.Dept;
import com.banyue.grpc.dept.DeptRequest;
import com.banyue.grpc.dept.DeptResponse;
import com.banyue.grpc.dept.DeptServiceGrpc;
import com.banyue.service.DeptBaseService;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author: zhangsp
 * @date: 2023/4/16 18:16
 * @description:
 */
@GrpcService
public class GrpcDeptService extends DeptServiceGrpc.DeptServiceImplBase{


    @Autowired
    private DeptBaseService deptBaseService;

    @Override
    public void queryDeptList(DeptRequest request, StreamObserver<DeptResponse> responseObserver) {

        List<com.banyue.api.Vo.Dept> depts = deptBaseService.selectAll();

        List<Dept> deptList = new ArrayList<>();

        depts.forEach(e->{
            deptList.add(Dept.newBuilder().setCode(String.valueOf(e.getDeptNo())).setName(e.getDeptName()).build());
        });

        DeptResponse.Builder responseBuilder = DeptResponse.newBuilder().setMessage("查询成功").setCode(200);

        responseBuilder.addAllDeptList(deptList);

        DeptResponse response = responseBuilder.build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<DeptRequest> sendAndReply(StreamObserver<DeptResponse> responseObserver) {
        return new StreamObserver<DeptRequest>() {
            @SneakyThrows
            @Override
            public void onNext(DeptRequest request) {
                // 处理每个请求
                DeptResponse response = DeptResponse.newBuilder()
                        .setMessage("Hello, " + request.getName())
                        .build();
                TimeUnit.MILLISECONDS.sleep(1000);
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                // 处理错误
            }

            @Override
            public void onCompleted() {
                // 处理完成事件
                responseObserver.onCompleted();
            }
        };
    }
}
