package com.wjl.stream;

import com.wjl.lambda.Employee;
import com.wjl.lambda.Employee.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author wangJiaLun
 * @date 2021-09-08
 **/
public class TestStreamAPI {

    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 79, 6666.66, Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );

    /*
           1. 给定一个数字列表 1,2,3,4,5 输出 平方列表
     */
    @Test
    public void test(){
        List<Integer> numList = Arrays.asList(1,2,3,4,5);
        numList.stream()
                .map(x -> x*x)
                .forEach(System.out::println);
    }

    /*
        2. 使用 map 和 reduce 获取 emps中 Employee数量
     */
    @Test
    public void test1(){
        Optional<Integer> count = emps.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(count.get());
    }


}
