package com.example.transaction.myJsonserialize;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

    public class JsonInterceptor implements InvocationHandler {

    private final MyJsonService target;

    public JsonInterceptor(MyJsonService target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.getName().equals("writeAsString")) return method.invoke(target, args);
        String temp = "";
        temp += "{";

        MyJsonDomain myJsonDomain = null;
        if (!ObjectUtils.isEmpty(args)) {
            myJsonDomain = (MyJsonDomain) args[0];
            Field[] fields = myJsonDomain.getClass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                if (AnnotationUtils.findAnnotation(fields[i], MyJsonSerialize.class) != null) {
                    temp += fields[i].getName();
                    temp += " : ";
                    temp += fields[i].get(myJsonDomain);
                    if (i != (fields.length - 1)) {
                         temp += ", ";
                    }
                }
            }
        }
        temp += "}";

        Object invoke = null;
        if (method.getReturnType() == String.class) invoke = temp;
        else invoke = method.invoke(target, args);
        return invoke;
    }
}
