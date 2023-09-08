package com.banyue.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author: zhangsp
 * @date: 2023/4/10 16:01
 * @description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface GrpcService {

//    Class<? extends ServerInterceptor>[] interceptors() default {};
//
//    String[] interceptorNames() default {};
//
//    boolean sortInterceptors() default false;
}
