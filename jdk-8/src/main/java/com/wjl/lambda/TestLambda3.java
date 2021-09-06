package com.wjl.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangJiaLun
 * @date 2021-09-03
 **/
public class TestLambda3 {

    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 18, 999),
            new Employee("lisi", 38, 1299),
            new Employee("wangwu", 23, 299),
            new Employee("cs1", 36, 12.49),
            new Employee("cs2", 11, 3199)
    );


    @Test
    public void test(){
        employees.sort((e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        employees.forEach(System.out::println);
    }
}
