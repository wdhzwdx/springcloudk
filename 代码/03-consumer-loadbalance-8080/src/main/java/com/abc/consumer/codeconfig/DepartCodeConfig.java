package com.abc.consumer.codeconfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartCodeConfig {

    @Bean
    public IRule loadBalanceRule() {
        return new RandomRule();
    }

    // @Bean
    // public IRule loadBalanceRule() {
    //     List<Integer> excludePorts = new ArrayList<>();
    //     excludePorts.add(8083);
    //     return new CustomRule(excludePorts);
    // }
}
