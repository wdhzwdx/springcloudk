package com.abc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer008200 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServer008200.class, args);
    }
}
