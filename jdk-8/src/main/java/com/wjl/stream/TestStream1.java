package com.wjl.stream;

import com.wjl.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *  Stream 三个操作步骤
 *      1. 创建 Stream
 *      2. 中间操作
 *      3. 终止操作
 * @author wangJiaLun
 * @date 2021-09-07
 **/
public class TestStream1 {


    // 创建 Stream
    @Test
    public void test1(){
        // 1. 可以通过 Collection 系列集合提供的 stream() 或者 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2. 通过 Arrays 中的 静态方法 stream() 获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(emps);

        // 3. 通过 Stream 类中的静态方法 of()
        Stream<String> stream2 = Stream.of("aaa", "bbb", "ccc");
        Stream<Employee> stream3 = Stream.of(emps);

        // 4. 创建无限流
        // 迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);

        // 生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
