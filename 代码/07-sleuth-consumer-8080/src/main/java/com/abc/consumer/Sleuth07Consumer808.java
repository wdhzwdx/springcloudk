package com.abc.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 指定Feign接口所在的包
@EnableFeignClients(basePackages = "com.abc.consumer.service")
@SpringBootApplication
public class Sleuth07Consumer808 {

    public static void main(String[] args) {
        SpringApplication.run(Sleuth07Consumer808.class, args);
    }

}
