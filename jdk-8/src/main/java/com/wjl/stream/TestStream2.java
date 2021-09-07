package com.wjl.stream;

import com.wjl.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
public class TestStream2 {

    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 18, 999),
            new Employee("lisi", 38, 1299),
            new Employee("wangwu", 23, 299),
            new Employee("cs1", 36, 112.49),
            new Employee("cs1", 36, 112.49),
            new Employee("cs2", 11, 3199)
    );

    // 中间操作
    /*
	  筛选与切片
		filter——接收 Lambda ， 从流中排除某些元素。
		limit——截断流，使其元素不超过给定数量。
		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 */
    // 内部迭代, 迭代操作由 Stream API 完成
    @Test
    public void test1(){
        // 中间操作
        Stream<Employee> employeeStream = employees.stream()
                .filter((e) ->{
                    // 延迟加载, 只有执行终止操作时才会被执行 (惰性求值)
                    System.out.println("Stream API 的中间操作 ");
                    return e.getAge() > 35;
                });
        // 终止操作: 一次性执行全部操作
        employeeStream.forEach(System.out::println);
    }
    // 外部迭代
    @Test
    public void test2(){
        Iterator<Employee> it = employees.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    @Test
    public void test3(){
        employees.stream()
                .filter(e ->{
                    System.out.println("短路");
                    return e.getSalary() > 300;
                })
                .limit(1)
                .forEach(System.out::println);
    }

    @Test
    public void test4(){
        employees.stream()
                .distinct()
                .filter(e ->{
                    System.out.println("短路");
                    return e.getSalary() > 30;
                })
                .skip(1)
                .forEach(System.out::println);
    }

    	/*
		映射
		map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
		flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
	 */
    @Test
    public void test5(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream()
                .map((String::toUpperCase))
                .forEach(System.out::println);

        System.out.println("------------------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("------------------------------");

        Stream<Stream<Character>> streamStream = list.stream()
                .map(TestStream2::filterCharacter);
        streamStream.forEach(s-> {
            s.forEach(System.out::println);
        });
        // flatMap 嵌套流变成追加写入
        Stream<Character> characterStream = list.stream()
                .flatMap(TestStream2::filterCharacter);
        characterStream.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    // 中间操作
    /*
        sorted()——自然排序
        sorted(Comparator com)——定制排序
     */
    @Test
    public void test(){
        employees.stream()
                .map(Employee::getName)
                .sorted()
                .forEach(System.out::println);

        System.out.println("------------------------------------");

        employees.stream()
                .sorted((x, y) -> {
                    if(x.getAge() == y.getAge()){
                        return x.getName().compareTo(y.getName());
                    }else{
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                }).forEach(System.out::println);
    }
}
