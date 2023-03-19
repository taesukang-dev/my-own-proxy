package com.example.transaction.myJsonserialize;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MyJsonDomain {
    @MyJsonSerialize
    private int id;
    @MyJsonSerialize
    private String name;
    @MyJsonSerialize
    private boolean isIt;

    public MyJsonDomain(int id, String name, boolean isIt) {
        this.id = id;
        this.name = name;
        this.isIt = isIt;
    }

    @Override
    public String toString() {
        return "MyJsonDomain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isIt=" + isIt +
                '}';
    }
}
