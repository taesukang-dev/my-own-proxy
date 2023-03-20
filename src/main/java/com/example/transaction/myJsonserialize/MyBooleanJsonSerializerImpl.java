package com.example.transaction.myJsonserialize;

public class MyBooleanJsonSerializerImpl implements MyJsonSerializer<Boolean> {
    @Override
    public String serialize(Boolean value) {
        return value ? "false" : "true";
    }
}
