package com.example.transaction.myJsonserialize.serializer;

public interface MyJsonSerializer<T> {
    String serialize(T value);
}
