package com.abc.producer;

import com.abc.source.CustomSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding({Source.class, CustomSource.class})   // 绑定到多个Channel
public class SomeProducer {
    @Autowired
    @Qualifier(Source.OUTPUT)       // 指定要绑定的Channel
    private MessageChannel channel;

    @Autowired
    @Qualifier(CustomSource.CHANNEL_NAME)     // 指定要绑定的Channel
    private MessageChannel customChannel;

    public void sendMsg(String message) {
        channel.send(MessageBuilder.withPayload(message).build());
        customChannel.send(MessageBuilder.withPayload(message).build());
    }
}
