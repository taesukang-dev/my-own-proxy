package com.example.transaction.myJsonserialize;

import com.fasterxml.jackson.databind.JsonSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyJsonSerialize {
    public Class using() default JsonSerializer.None.class;
}
