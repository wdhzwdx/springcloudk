package com.abc.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringCloudApplication
@EnableHystrixDashboard    // 开启Hystrix仪表盘功能
public class Consumer04Dashboard8080 {

    public static void main(String[] args) {
        SpringApplication.run(Consumer04Dashboard8080.class, args);
    }

}
