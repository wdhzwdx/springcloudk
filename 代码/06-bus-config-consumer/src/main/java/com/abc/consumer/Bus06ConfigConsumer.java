package com.abc.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 指定Service接口所在的包，开启OpenFeign客户端
@EnableFeignClients(basePackages = "com.abc.consumer.service")
@SpringCloudApplication
public class Bus06ConfigConsumer {

    public static void main(String[] args) {
        SpringApplication.run(Bus06ConfigConsumer.class, args);
    }

}
