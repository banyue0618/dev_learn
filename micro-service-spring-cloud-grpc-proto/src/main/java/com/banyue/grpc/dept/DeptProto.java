// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: dept/DeptProto.proto

package com.banyue.grpc.dept;

public final class DeptProto {
  private DeptProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DeptRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DeptRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DeptResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DeptResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Dept_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Dept_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024dept/DeptProto.proto\032\023bean/BaseBean.pr" +
      "oto\032\031google/protobuf/any.proto\"@\n\013DeptRe" +
      "quest\022\n\n\002id\030\001 \001(\003\022\014\n\004name\030\002 \001(\t\022\027\n\004bean\030" +
      "\003 \001(\0132\t.BaseBean\"F\n\014DeptResponse\022\014\n\004code" +
      "\030\001 \001(\003\022\017\n\007message\030\002 \001(\t\022\027\n\010deptList\030\003 \003(" +
      "\0132\005.Dept\"\"\n\004Dept\022\014\n\004code\030\001 \001(\t\022\014\n\004name\030\002" +
      " \001(\t2p\n\013DeptService\022.\n\rqueryDeptList\022\014.D" +
      "eptRequest\032\r.DeptResponse\"\000\0221\n\014sendAndRe" +
      "ply\022\014.DeptRequest\032\r.DeptResponse\"\000(\0010\001B#" +
      "\n\024com.banyue.grpc.deptB\tDeptProtoP\001b\006pro" +
      "to3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.banyue.grpc.bean.base.BaseBeanProto.getDescriptor(),
          com.google.protobuf.AnyProto.getDescriptor(),
        });
    internal_static_DeptRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_DeptRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DeptRequest_descriptor,
        new java.lang.String[] { "Id", "Name", "Bean", });
    internal_static_DeptResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_DeptResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DeptResponse_descriptor,
        new java.lang.String[] { "Code", "Message", "DeptList", });
    internal_static_Dept_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_Dept_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Dept_descriptor,
        new java.lang.String[] { "Code", "Name", });
    com.banyue.grpc.bean.base.BaseBeanProto.getDescriptor();
    com.google.protobuf.AnyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
