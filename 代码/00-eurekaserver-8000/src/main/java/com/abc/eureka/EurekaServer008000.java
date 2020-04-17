package com.abc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer   // 开启Eureka Server
public class EurekaServer008000 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer008000.class, args);
    }
}
