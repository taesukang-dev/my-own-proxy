package com.example.transaction.myJsonserialize;

public interface MyJsonSerializer<T> {
    String serialize(T value);
}
