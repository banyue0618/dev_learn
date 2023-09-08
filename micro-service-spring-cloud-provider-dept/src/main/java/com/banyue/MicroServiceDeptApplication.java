package com.banyue;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.security.Security;

/**
 * @author: zhangsp
 * @date: 2023/3/23 16:38
 * @description:
 */

/**
 * 如果不添加包扫描路径，则需要在响应mapper文件添加相关注解，比如：@Mapper、@Repository,否则会报错提示找不到mapper
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.banyue.mapper")
public class MicroServiceDeptApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceDeptApplication.class);
    }
}
