package com.wjl.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 *  方法引用:  若 Lambda 体中的内容有方法已经实现了, 我们可以使用"方法引用:
 *                      (可以理解成方法引用是 Lambda 表达式的另外一种表现形式)
 *   主要有三种语法格式:
 *      对象:: 实例方法名
 *
 *      类::静态方法名
 *
 *      类::实例方法名
 *   注意:
 *      1. Lambda 体中调用方法的参数列表与返回值类型需要与函数式接口中抽象方法函数的参数列表和返回值类型保持一致
 *      2. Lambda 参数列表中的第一参数是实例方法的调用者, 第二个参数是实例方法的参数时, 可以使用 ClassName:: method
 *
 *   构造器引用:
 *   格式:
 *      ClassName :: new
 *
 *   数组引用:
 *     Type:: new
 * @author wangJiaLun
 * @date 2021-09-06
 **/
public class TestMethodRef {

    // 对象::实例方法名
    @Test
    public void test1(){
        Consumer<String> con = (x) -> System.out.println(x);

        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;

        Consumer<String> con2 = System.out::println;
        con2.accept("hello world");
    }

    @Test
    public void test2(){
        Employee employee = new Employee();
        employee.setAge(1);
        employee.setName("hello world");
        Supplier<String> sup = () -> employee.getName();
        System.out.println(sup.get());

        Supplier<Integer> sup2 = employee::getAge;
        Integer age = sup2.get();
        System.out.println(age);
    }

    // 类::静态方法名
    @Test
    public void test3(){
        Comparator<Integer> com = (x, y ) -> Integer.compare(x, y);
        Comparator<Integer> com1 = Integer::compare;

    }

    // 类:: 实例方法名
    @Test
    public void test4(){
        // 第一参数是实例方法的调用者, 第二个参数是实例方法的参数时, 可以使用 ClassName:: method
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
        BiPredicate<String, String> biPredicate1= String::equals;

        BiPredicate<String, String> biPredicate2 = (x, y) -> y.equals(x);
    }

    // 构造器引用
    @Test
    public void test5(){
        Supplier<Employee> sup = () -> new Employee();
        // 无参构造器引用
        Supplier<Employee> sup2 = Employee::new;
        Supplier<Employee> sup3 = () -> new Employee("1",1,1);

        // 单参构造器
        Function<Integer, Employee> fun = (x) -> new Employee(x);
        Function<Integer, Employee> fun1 = Employee::new;
        Employee employee = fun1.apply(101);
        System.out.println(employee);

    }

    // 数组引用
    @Test
    public void test6(){
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);

        Function<Integer, String[]> fun1 = String[]::new;
        String[] strs1 = fun.apply(20);
        System.out.println(strs1.length);
    }
}
