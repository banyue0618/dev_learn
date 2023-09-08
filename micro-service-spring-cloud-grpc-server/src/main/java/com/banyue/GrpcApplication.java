package com.banyue;

import com.banyue.annotation.GrpcService;
import com.banyue.manage.ServiceManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Map;

/**
 * @author: zhangsp
 * @date: 2023/4/10 16:08
 * @description:
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
public class GrpcApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(GrpcApplication.class);
//        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(GrpcApplication.class, args);
//        Map<String, Object> grpcServiceBeanMap = configurableApplicationContext.getBeansWithAnnotation(GrpcService.class);
//        ServiceManager serviceManager = configurableApplicationContext.getBean(ServiceManager.class);
//        serviceManager.loadService(grpcServiceBeanMap);
    }

}
