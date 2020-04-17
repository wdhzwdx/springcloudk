package com.abc.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer   // 开启ConfigServer
@SpringBootApplication
public class ConfigServer009999 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServer009999.class, args);
    }
}
