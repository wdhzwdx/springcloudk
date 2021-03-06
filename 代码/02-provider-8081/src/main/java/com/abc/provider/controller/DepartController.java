package com.abc.provider.controller;

import com.abc.provider.bean.Depart;
import com.abc.provider.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/provider/depart")
@RestController
public class DepartController {
    @Autowired
    private DepartService service;

    // 声明服务发现client
    @Autowired
    private DiscoveryClient client;

    @PostMapping("/save")
    public boolean saveHandle(@RequestBody Depart depart) {
        return service.saveDepart(depart);
    }

    @DeleteMapping("/del/{id}")
    public boolean deleteHandle(@PathVariable("id") int id) {
        return service.removeDepartById(id);
    }

    @PutMapping("/update")
    public boolean updateHandle(@RequestBody Depart depart) {
        return service.modifyDepart(depart);
    }

    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        return service.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> listHandle() {
        return service.listAllDeparts();
    }

    @GetMapping("/discovery")
    public Object discoveryHandle() {
        // 获取注册表中所有的微服务名称
        List<String> springApplicationNames = client.getServices();
        // 遍历所有微服务名称
        for(String name: springApplicationNames) {
            // 根据微服务名称获取该微服务的所有提供者主机
            List<ServiceInstance> instances = client.getInstances(name);
            // 遍历所有主机，获取所有主机信息
            for(ServiceInstance instance: instances) {
                String host = instance.getHost();
                int port = instance.getPort();
                System.out.println(host + " : " + port);
            }
        }
        return springApplicationNames;
    }
}
