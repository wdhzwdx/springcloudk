package com.abc.controller;

import com.abc.producer.SomeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeController {
    @Autowired
    private SomeProducer producer;

    @PostMapping("/msg/send")
    public String sendMsg(@RequestParam("message") String message) {
        producer.sendMsg(message);
        return "send success";
    }
}
