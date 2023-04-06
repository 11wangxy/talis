package com.example.talis.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)//定义生效时间，运行时候有效
@Target(ElementType.METHOD)//方法有效
public @interface log {
}
