package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringCloudStream08Kafka {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStream08Kafka.class, args);
    }

}

