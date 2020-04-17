package com.abc.consumer.controller;

import com.abc.consumer.bean.Depart;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/save")
    public boolean saveHandle(@RequestBody Depart depart) {
        String url = "http://localhost:8081/provider/depart/save";
        return restTemplate.postForObject(url, depart, Boolean.class);
    }

    @DeleteMapping("/del/{id}")
    public void deleteHandle(@PathVariable("id") int id) {
        String url = "http://localhost:8081/provider/depart/del/" + id;
        restTemplate.delete(url);
    }

    @PutMapping("/update")
    public void updateHandle(@RequestBody Depart depart) {
        String url = "http://localhost:8081/provider/depart/update";
        restTemplate.put(url, depart);
    }

    // 指定当前处理器方法启用了服务降级机制
    @HystrixCommand(fallbackMethod = "getHystrixHandle")
    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        String url = "http://localhost:8081/provider/depart/get/" + id;
        return restTemplate.getForObject(url, Depart.class);
    }

    // 服务降级方法
    public Depart getHystrixHandle(@PathVariable("id") int id) {
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart");
        return depart;
    }


    @GetMapping("/list")
    public List<Depart> listHandle() {
        String url = "http://localhost:8081/provider/depart/list";
        return restTemplate.getForObject(url, List.class);
    }
}
