package com.example.transaction.myJsonserialize;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
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
                        MyJsonSerializer<Serializable> myJsonSerializer = (MyJsonSerializer<Serializable>) serializer.getConstructor().newInstance();
                        temp += selectType(fields[i], myJsonSerializer, myJsonDomain);
                    } else temp += fields[i].get(myJsonDomain);

                    if (i != (fields.length - 1)) temp += ", ";
                }
            }
        }

        temp += "}";

        Object invoke = null;
        if (method.getReturnType() == String.class) invoke = temp;
        else invoke = method.invoke(target, args);
        return invoke;
    }

    private String selectType(Field field, MyJsonSerializer<Serializable> myJsonSerializer, MyJsonDomain myJsonDomain) throws IllegalAccessException {
        if(field.getType().getSimpleName().equals("int")) return myJsonSerializer.serialize((Integer) field.get(myJsonDomain));
        else if(field.getType().getSimpleName().equals("String")) return myJsonSerializer.serialize((String) field.get(myJsonDomain));
        return myJsonSerializer.serialize((boolean) field.get(myJsonDomain));
    }
}
