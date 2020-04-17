package com.abc.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringCloudApplication
public class Consumer04FeignFallbackMethod8080 {

    public static void main(String[] args) {
        SpringApplication.run(Consumer04FeignFallbackMethod8080.class, args);
    }

}
