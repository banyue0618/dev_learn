package com.banyue.utils;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

/**
 * @author: zhangsp
 * @date: 2023/4/17 18:23
 * @description:
 */
public class ProtoJsonUtil {

    /**
     * proto 对象转 JSON
     * 使用方法： //反序列化之后
     *             UserProto.User user1 = UserProto.User.parseFrom(user);
     *             //转 json
     *             String jsonObject = ProtoJsonUtil.toJson(user1);
     * @param sourceMessage proto 对象
     * @return 返回 JSON 数据
     * @throws InvalidProtocolBufferException
     */
    public static String toJson(Message sourceMessage) throws InvalidProtocolBufferException {
        if (sourceMessage != null) {
            String json = JsonFormat.printer().includingDefaultValueFields().print(sourceMessage);
            return json;
        }
        return null;
    }

    /**
     * JSON 转 proto 对象
     * 使用方法：Message message = ProtoJsonUtil.toObject(UserProto.User.newBuilder(), jsonObject);
     * @param targetBuilder proto 对象 bulider
     * @param json          json 数据
     * @return 返回转换后的 proto 对象
     * @throws InvalidProtocolBufferException
     */
    public static Message toObject(Message.Builder targetBuilder, String json) throws InvalidProtocolBufferException {
        if (json != null) {
            //ignoringUnknownFields 如果 json 串中存在的属性 proto 对象中不存在，则进行忽略，否则会抛出异常
            JsonFormat.parser().ignoringUnknownFields().merge(json, targetBuilder);
            return targetBuilder.build();
        }
        return null;
    }

}
