package com.abc.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 指定Feign接口所在的包
@EnableFeignClients(basePackages = "com.abc.consumer.service")
@SpringBootApplication
public class Kafka07SleuthConsumer8080 {

    public static void main(String[] args) {
        SpringApplication.run(Kafka07SleuthConsumer8080.class, args);
    }

}
