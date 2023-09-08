package com.banyue.annotation;

import java.lang.annotation.*;

/**
 * @author: zhangsp
 * @date: 2023/6/12 10:08
 * @description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface LogRecordAnnotation {

    /**
     * 操作日志文本模板
     * @return
     */
    String success();

    /**
     * 操作日志失败的模板
     * @return
     */
    String fail() default "";

    /**
     * 操作日志执行人
     * @return
     */
    String operator() default "";

    /**
     * 业务字段
     * @return
     */
    String bizNo();

    /**
     * 操作日志种类
     * @return
     */
    String category() default "";

    /**
     * 记录操作日志详情
     * @return
     */
    String detail() default "";

    /**
     * 记录日志的条件
     * @return
     */
    String condition() default "";

}
