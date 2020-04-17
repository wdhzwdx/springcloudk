package com.abc.consumer.controller;

import com.abc.consumer.bean.Depart;
import com.abc.consumer.service.DepartService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    @Autowired
    private DepartService service;
    @Autowired
    private StringRedisTemplate template;

    // 创建一个线程池，包含5个线程
    private ForkJoinPool pool = new ForkJoinPool(5);

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

    @HystrixCommand(fallbackMethod = "getHystrixHandle")
    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id, HttpServletRequest request) {
        return service.getDepartById(id);
    }

    public Depart getHystrixHandle(@PathVariable("id") int id, HttpServletRequest request) {
        // 将本机IP与方法名组合为redis中的key
        String ip = request.getLocalAddr();
        String key = ip + "_getDepartById";
        alarm(key);

        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart");
        return depart;
    }

    private void alarm(String key) {
        // 获取Redis接口对象
        BoundValueOperations<String, String> ops = template.boundValueOps(key);

        // 使用双重检测锁机制解决Redis的“热点缓存”问题
        String value = ops.get();
        if(value == null) {
            // 1, 1
            synchronized (this) {
                value = ops.get();
                if(value == null) {
                    // 发送短信
                    sendFallbackMsg(key);
                    value = "已发生服务降级";
                    ops.set(value, 10, TimeUnit.SECONDS);
                }
            }
        }
    }

    private void sendFallbackMsg(String key) {

        pool.submit(() -> {
            System.out.println("发送服务异常报警短信：" + key);
        });

    }

    @GetMapping("/list")
    public List<Depart> listHandle() {
        return service.listAllDeparts();
    }
}
