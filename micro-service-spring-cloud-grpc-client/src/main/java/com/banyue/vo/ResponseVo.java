package com.banyue.vo;

import com.banyue.grpc.dept.Dept;

import java.util.List;

/**
 * @author: zhangsp
 * @date: 2023/4/19 10:52
 * @description:
 */
public class ResponseVo<T> {

    private String code;

    private String message;

    private T data;

    public ResponseVo(String code, String message, T data) {
        this.code = code;

        this.message = message;

        this.data = data;
    }
}
