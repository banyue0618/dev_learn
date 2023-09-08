package com.banyue.grpc.dept;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * 服务定义
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: dept/DeptProto.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DeptServiceGrpc {

  private DeptServiceGrpc() {}

  public static final String SERVICE_NAME = "DeptService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.banyue.grpc.dept.DeptRequest,
      com.banyue.grpc.dept.DeptResponse> getQueryDeptListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "queryDeptList",
      requestType = com.banyue.grpc.dept.DeptRequest.class,
      responseType = com.banyue.grpc.dept.DeptResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.banyue.grpc.dept.DeptRequest,
      com.banyue.grpc.dept.DeptResponse> getQueryDeptListMethod() {
    io.grpc.MethodDescriptor<com.banyue.grpc.dept.DeptRequest, com.banyue.grpc.dept.DeptResponse> getQueryDeptListMethod;
    if ((getQueryDeptListMethod = DeptServiceGrpc.getQueryDeptListMethod) == null) {
      synchronized (DeptServiceGrpc.class) {
        if ((getQueryDeptListMethod = DeptServiceGrpc.getQueryDeptListMethod) == null) {
          DeptServiceGrpc.getQueryDeptListMethod = getQueryDeptListMethod =
              io.grpc.MethodDescriptor.<com.banyue.grpc.dept.DeptRequest, com.banyue.grpc.dept.DeptResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "queryDeptList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.banyue.grpc.dept.DeptRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.banyue.grpc.dept.DeptResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeptServiceMethodDescriptorSupplier("queryDeptList"))
              .build();
        }
      }
    }
    return getQueryDeptListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.banyue.grpc.dept.DeptRequest,
      com.banyue.grpc.dept.DeptResponse> getSendAndReplyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendAndReply",
      requestType = com.banyue.grpc.dept.DeptRequest.class,
      responseType = com.banyue.grpc.dept.DeptResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.banyue.grpc.dept.DeptRequest,
      com.banyue.grpc.dept.DeptResponse> getSendAndReplyMethod() {
    io.grpc.MethodDescriptor<com.banyue.grpc.dept.DeptRequest, com.banyue.grpc.dept.DeptResponse> getSendAndReplyMethod;
    if ((getSendAndReplyMethod = DeptServiceGrpc.getSendAndReplyMethod) == null) {
      synchronized (DeptServiceGrpc.class) {
        if ((getSendAndReplyMethod = DeptServiceGrpc.getSendAndReplyMethod) == null) {
          DeptServiceGrpc.getSendAndReplyMethod = getSendAndReplyMethod =
              io.grpc.MethodDescriptor.<com.banyue.grpc.dept.DeptRequest, com.banyue.grpc.dept.DeptResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendAndReply"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.banyue.grpc.dept.DeptRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.banyue.grpc.dept.DeptResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DeptServiceMethodDescriptorSupplier("sendAndReply"))
              .build();
        }
      }
    }
    return getSendAndReplyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DeptServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeptServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeptServiceStub>() {
        @java.lang.Override
        public DeptServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeptServiceStub(channel, callOptions);
        }
      };
    return DeptServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DeptServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeptServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeptServiceBlockingStub>() {
        @java.lang.Override
        public DeptServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeptServiceBlockingStub(channel, callOptions);
        }
      };
    return DeptServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DeptServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DeptServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DeptServiceFutureStub>() {
        @java.lang.Override
        public DeptServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DeptServiceFutureStub(channel, callOptions);
        }
      };
    return DeptServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * 服务定义
   * </pre>
   */
  public static abstract class DeptServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 方法，格式为"方法名 请求参数 返回参数"
     * </pre>
     */
    public void queryDeptList(com.banyue.grpc.dept.DeptRequest request,
        io.grpc.stub.StreamObserver<com.banyue.grpc.dept.DeptResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getQueryDeptListMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.banyue.grpc.dept.DeptRequest> sendAndReply(
        io.grpc.stub.StreamObserver<com.banyue.grpc.dept.DeptResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getSendAndReplyMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryDeptListMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.banyue.grpc.dept.DeptRequest,
                com.banyue.grpc.dept.DeptResponse>(
                  this, METHODID_QUERY_DEPT_LIST)))
          .addMethod(
            getSendAndReplyMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                com.banyue.grpc.dept.DeptRequest,
                com.banyue.grpc.dept.DeptResponse>(
                  this, METHODID_SEND_AND_REPLY)))
          .build();
    }
  }

  /**
   * <pre>
   * 服务定义
   * </pre>
   */
  public static final class DeptServiceStub extends io.grpc.stub.AbstractAsyncStub<DeptServiceStub> {
    private DeptServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeptServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeptServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 方法，格式为"方法名 请求参数 返回参数"
     * </pre>
     */
    public void queryDeptList(com.banyue.grpc.dept.DeptRequest request,
        io.grpc.stub.StreamObserver<com.banyue.grpc.dept.DeptResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getQueryDeptListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.banyue.grpc.dept.DeptRequest> sendAndReply(
        io.grpc.stub.StreamObserver<com.banyue.grpc.dept.DeptResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getSendAndReplyMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * 服务定义
   * </pre>
   */
  public static final class DeptServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<DeptServiceBlockingStub> {
    private DeptServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeptServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeptServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 方法，格式为"方法名 请求参数 返回参数"
     * </pre>
     */
    public com.banyue.grpc.dept.DeptResponse queryDeptList(com.banyue.grpc.dept.DeptRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getQueryDeptListMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * 服务定义
   * </pre>
   */
  public static final class DeptServiceFutureStub extends io.grpc.stub.AbstractFutureStub<DeptServiceFutureStub> {
    private DeptServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeptServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DeptServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 方法，格式为"方法名 请求参数 返回参数"
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.banyue.grpc.dept.DeptResponse> queryDeptList(
        com.banyue.grpc.dept.DeptRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getQueryDeptListMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_DEPT_LIST = 0;
  private static final int METHODID_SEND_AND_REPLY = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DeptServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DeptServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QUERY_DEPT_LIST:
          serviceImpl.queryDeptList((com.banyue.grpc.dept.DeptRequest) request,
              (io.grpc.stub.StreamObserver<com.banyue.grpc.dept.DeptResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_AND_REPLY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendAndReply(
              (io.grpc.stub.StreamObserver<com.banyue.grpc.dept.DeptResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DeptServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DeptServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.banyue.grpc.dept.DeptProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DeptService");
    }
  }

  private static final class DeptServiceFileDescriptorSupplier
      extends DeptServiceBaseDescriptorSupplier {
    DeptServiceFileDescriptorSupplier() {}
  }

  private static final class DeptServiceMethodDescriptorSupplier
      extends DeptServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DeptServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DeptServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DeptServiceFileDescriptorSupplier())
              .addMethod(getQueryDeptListMethod())
              .addMethod(getSendAndReplyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
