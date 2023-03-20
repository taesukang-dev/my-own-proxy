package com.example.transaction.myJsonserialize.serializer;

public class MyBooleanJsonSerializerImpl implements MyJsonSerializer<Boolean> {
    @Override
    public String serialize(Boolean value) {
        return value ? "false" : "true";
    }
}
