package com.abc.consumer.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Depart {
    private Integer id;
    private String name;

    public Depart(String name) {
        this.name = name;
    }
}
