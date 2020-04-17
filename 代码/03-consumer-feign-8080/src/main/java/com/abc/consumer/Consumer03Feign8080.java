package com.abc.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients   // 开启所有Feign客户端
@SpringBootApplication
public class Consumer03Feign8080 {

    public static void main(String[] args) {
        SpringApplication.run(Consumer03Feign8080.class, args);
    }

}











