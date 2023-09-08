package com.banyue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: zhangsp
 * @date: 2023/3/23 16:13
 * @description:
 */
@SpringBootApplication
@EnableEurekaServer
public class MicroServiceEurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceEurekaServer.class);
    }

}
