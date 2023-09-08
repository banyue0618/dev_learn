package com.banyue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: zhangsp
 * @date: 2023/4/10 16:37
 * @description:
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
@EnableEurekaClient
@EnableDiscoveryClient
public class GrpcClientApplication {

    public static void main(String[] args) {
//        System.setProperty("javax.net.debug", "ssl:handshake");
//        System.setProperty("javax.net.ssl.keyStore", "classpath:keys/local/server.p12");
//        System.setProperty("javax.net.ssl.keyStorePassword", "123456");
        SpringApplication.run(GrpcClientApplication.class);
    }

}
