package com.banyue.demo;

import com.banyue.demo.common.Color;
import com.banyue.demo.common.EnumValueValid;

/**
 * @Description 校验入参字段的value是否在指定的枚举类中
 * @Author zhangsip
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2024/10/16
 */
public class ParameterValidDemo {

    @EnumValueValid(enumClass= Color.class)
    private String color;

}
