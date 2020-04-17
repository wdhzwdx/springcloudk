package com.abc.consumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;

import javax.annotation.PostConstruct;

// @Component
// @EnableBinding(Sink.class)
public class SomeConsumer {
    @Autowired
    @Qualifier(Sink.INPUT)     // 指定要绑定的输入Channel名称
    private SubscribableChannel channel;

    @PostConstruct
    public void printMSG() {
        channel.subscribe(message -> {
                System.out.println(message.getHeaders() + " , ");
                byte[] payload = (byte[])message.getPayload();
                System.out.println(new String(payload));
        });
    }
}
