package com.abc.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy    // 开启zuul代理模式
@SpringBootApplication
public class Zuul00Gray9000 {

    public static void main(String[] args) {
        SpringApplication.run(Zuul00Gray9000.class, args);
    }

}
