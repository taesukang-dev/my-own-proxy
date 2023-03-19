package com.example.transaction.config;

import com.example.transaction.myJsonserialize.JsonInterceptor;
import com.example.transaction.myJsonserialize.MyJson;
import com.example.transaction.myJsonserialize.MyJsonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class JsonConfig {

    @Bean // ObjectMapper 라는 가정
    public MyJson myJsonService() {
        MyJsonService myJson = new MyJsonService();
        return (MyJson) Proxy.newProxyInstance(MyJson.class.getClassLoader(),
                new Class[]{MyJson.class},
                new JsonInterceptor(myJson));
    }

}
