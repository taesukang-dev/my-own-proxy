package com.example.transaction.myJsonserialize;

public class MyJsonService implements MyJson{

    @Override // writeAsString 이라는 가정
    public String writeAsString(MyJsonDomain myJsonDomain) {
        return "ok";
    }
}
