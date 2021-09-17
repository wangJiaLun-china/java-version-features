package com.wjl.annotation;

import lombok.NonNull;
import org.junit.Test;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

/**
 *  重复注解与注解类型
 * @author wangJiaLun
 * @date 2021-09-17
 **/
public class TestAnnotation {


    /**
     *  可重复注解 : 配置 Repeatable 容器
     */
    @MyAnnotation("Hello1")
    @MyAnnotation("Hello2")
    public void show(@MyAnnotation("abc") String str){

    }

    @Test
    public void test1() throws NoSuchMethodException {
        Class<TestAnnotation>clazz = TestAnnotation.class;
        Method m1 = clazz.getMethod("show");
        MyAnnotation[] mas = m1.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation ma : mas) {
            System.out.println(ma.value());
        }
    }
}
