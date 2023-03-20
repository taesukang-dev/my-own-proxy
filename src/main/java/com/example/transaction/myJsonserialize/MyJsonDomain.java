package com.example.transaction.myJsonserialize;

import lombok.Getter;

@Getter
public class MyJsonDomain {
    @MyJsonSerialize(using = MyIntJsonSerializerImpl.class)
    private int id;
    @MyJsonSerialize(using = MyStringJsonSerializerImpl.class)
    private String name;
    @MyJsonSerialize(using = MyBooleanJsonSerializerImpl.class)
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
