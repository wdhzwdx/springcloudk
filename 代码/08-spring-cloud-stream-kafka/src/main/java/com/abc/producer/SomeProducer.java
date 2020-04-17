package com.abc.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)   // 将当前应用与输出Channel绑定到一起
public class SomeProducer {
    @Autowired
    @Qualifier(Source.OUTPUT)   // byName方式自动注入指定名称的Channel
    private MessageChannel channel;

    public void sendMsg(String message) {
        channel.send(MessageBuilder.withPayload(message).build());
    }
}


