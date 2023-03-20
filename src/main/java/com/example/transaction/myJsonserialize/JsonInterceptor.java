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

        if (!ObjectUtils.isEmpty(args)) {
            MyJsonDomain myJsonDomain = (MyJsonDomain) args[0];
            Field[] fields = myJsonDomain.getClass().getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                if (AnnotationUtils.findAnnotation(fields[i], MyJsonSerialize.class) != null) {
                    MyJsonSerialize annotation = AnnotationUtils.findAnnotation(fields[i], MyJsonSerialize.class);
                    temp += fields[i].getName();
                    temp += " : ";
                    if (!annotation.using().getSimpleName().equals("None")) {
                        Class<?> serializer = Class.forName("com.example.transaction.myJsonserialize." + annotation.using().getSimpleName());
                        MyJsonSerializer<java.io.Serializable> myJsonSerializer = (MyJsonSerializer<java.io.Serializable>) serializer.getConstructor().newInstance();
                        if (fields[i].getType().getSimpleName().equals("int")) temp += myJsonSerializer.serialize((Integer) fields[i].get(myJsonDomain));
                        if (fields[i].getType().getSimpleName().equals("String")) temp += myJsonSerializer.serialize((String) fields[i].get(myJsonDomain));
                        if (fields[i].getType().getSimpleName().equals("boolean"))temp += myJsonSerializer.serialize((boolean) fields[i].get(myJsonDomain));
                    } else {
                        temp += fields[i].get(myJsonDomain);
                    }
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
