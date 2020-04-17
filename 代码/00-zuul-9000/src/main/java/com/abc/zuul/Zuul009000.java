package com.abc.zuul;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy    // 开启zuul网关
@SpringBootApplication
public class Zuul009000 {

    public static void main(String[] args) {
        SpringApplication.run(Zuul009000.class, args);
    }

    // 设置负载均衡算法为“随机算法”
    @Bean
    public IRule loadBalanceRule() {
        return new RandomRule();
    }

}
