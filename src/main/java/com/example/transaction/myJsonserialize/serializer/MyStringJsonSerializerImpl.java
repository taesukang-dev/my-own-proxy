package com.example.transaction.myJsonserialize.serializer;

public class MyStringJsonSerializerImpl implements MyJsonSerializer<String> {
    @Override
    public String serialize(String value) {
        return value + " -> converted";
    }
}
