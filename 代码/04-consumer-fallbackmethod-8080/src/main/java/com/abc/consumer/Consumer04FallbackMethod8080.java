package com.abc.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;


// @EnableCircuitBreaker  // 开启熔断器
// @SpringBootApplication
@SpringCloudApplication
public class Consumer04FallbackMethod8080 {

    public static void main(String[] args) {
        SpringApplication.run(Consumer04FallbackMethod8080.class, args);
    }

}













