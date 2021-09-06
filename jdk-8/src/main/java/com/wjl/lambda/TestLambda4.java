package com.wjl.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author wangJiaLun
 * @date 2021-09-06
 **/
public class TestLambda4 {

    List<Employee> employees = Arrays.asList(
        new Employee("zhangsan", 18, 999),
        new Employee("lisi", 38, 1299),
        new Employee("wangwu", 23, 299),
        new Employee("cs1", 36, 12.49),
        new Employee("cs2", 11, 3199)
        );

    @Test
    public void test(){
        String result = strHandler("hello world", (str) -> str.toUpperCase(Locale.ROOT));
        System.out.println(result);
    }

    // 处理字符串
    public String strHandler(String str, MuFuncation mf){
        return mf.getValue(str);
    }

    @Test
    public void  test1(){
        System.out.println(op(1L, 2L, (l1,l2) -> l1+l2));
    }

    // 对两个Long型处理
    public Long op(Long l1, Long l2, MyFuncation2<Long, Long> mf){
        return mf.getValue(l1, l2);
    }

}
