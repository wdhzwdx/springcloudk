package com.abc.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableEurekaClient    // 只能连接Eureka Server
// @EnableDiscoveryClient   // 可以连接任意注册中心
@SpringBootApplication
public class Provider028081 {

    public static void main(String[] args) {
        SpringApplication.run(Provider028081.class, args);
    }
}
