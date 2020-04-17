package com.abc.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 指定Feign接口所在的包
@EnableFeignClients
@SpringBootApplication
public class Consumer03Loadbalance8080 {

    public static void main(String[] args) {
        SpringApplication.run(Consumer03Loadbalance8080.class, args);
    }

}
