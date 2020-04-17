package com.abc.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringCloudApplication
public class Zuul05Consumer8090 {

    public static void main(String[] args) {
        SpringApplication.run(Zuul05Consumer8090.class, args);
    }

}
