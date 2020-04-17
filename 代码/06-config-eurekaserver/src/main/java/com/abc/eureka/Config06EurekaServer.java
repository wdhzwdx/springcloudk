package com.abc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Config06EurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(Config06EurekaServer.class, args);
    }
}
