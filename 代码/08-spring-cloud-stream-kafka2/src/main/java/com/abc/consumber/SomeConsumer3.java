package com.abc.consumber;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class SomeConsumer3 {

    @StreamListener(Sink.INPUT)
    public void pringMSG(Object message) {
        System.out.println(message);
    }
}
