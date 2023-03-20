package com.example.transaction.myJsonserialize;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MyIntJsonSerializerImpl implements MyJsonSerializer<Integer>{
    @Override
    public String serialize(Integer value) {
        return (value + 1) + "";
    }
}
