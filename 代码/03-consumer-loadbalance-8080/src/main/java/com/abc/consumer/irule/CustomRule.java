package com.abc.consumer.irule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 自定义负载均衡策略：
 *   将指定的端口号排除后，对剩余的server采用随机选择策略
 */
public class CustomRule implements IRule {
    private ILoadBalancer lb;
    private List<Integer> excludePorts;
    public CustomRule() {
    }
    public CustomRule(List<Integer> excludePorts) {
        this.excludePorts = excludePorts;
    }
    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.lb = lb;
    }
    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }

    @Override
    public Server choose(Object key) {
        // 获取到所有UP状态server
        List<Server> servers = lb.getReachableServers();
        // 从所有UP状态server中获取到所有排除了指定端口号的server
        List<Server> availableServers = this.getAvailableServers(servers);
        // 对剩余server采用随机策略选择
        return this.getAvailableRandomServers(availableServers);
    }

    private List<Server> getAvailableServers(List<Server> servers) {
        if(excludePorts == null || excludePorts.size() == 0) {
            return servers;
        }

        List<Server> aservers = servers.stream()
                .filter(server -> excludePorts.stream().noneMatch(port -> server.getPort() == port))
                .collect(Collectors.toList());

        return aservers;
    }

    private Server getAvailableRandomServers(List<Server> availableServers) {
        int index = new Random().nextInt(availableServers.size());
        return availableServers.get(index);
    }
}
