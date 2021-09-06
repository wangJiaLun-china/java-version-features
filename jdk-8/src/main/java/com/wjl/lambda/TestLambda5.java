package com.wjl.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 内置的四大核心函数式接口
 *  Consumer<T>: 消费型接口
 *      void accept(T t);
 *  Supplier<T>: 供给型接口
 *      T get();
 *  Function<T, R>: 函数式接口
 *      R apply(T t);
 *  Predicate<T>: 断言型接口
 *      boolean test(T t);
 *
 * @author wangJiaLun
 * @date 2021-09-06
 **/
public class TestLambda5 {

    // Consumer<T> 消费型接口, 传一个对象执行一些操作, 没有返回值
    @Test
    public void test(){
        happy(10000, x -> System.out.println("hello world "+x));
    }
    public void happy(double money, Consumer<Double> con){
        con.accept(money);
    }

    // Supplier<T>: 供给型接口
    // 需求生产指定个数的整数放入集合
    public List<Integer> getNumList(int num, Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer e = sup.get();
            list.add(e);
        }
        return list;
    }

    @Test
    public void test2(){
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        numList.forEach(System.out::println);
    }

    // Function<T, R>: 函数式接口
    // 需求: 用于处理字符串
    @Test
    public void test3(){
        String result = strHandler("hello world\t", (s -> s.toUpperCase(Locale.ROOT)));
        System.out.println(result);
    }

    public String strHandler(String str , Function<String, String> fun){
        return fun.apply(str);
    }

    // Predicate<T>: 断言型接口
    // 需求: 将满足条件的字符串放入集合中
    @Test
    public void test4(){
        List<String> list = Arrays.asList("hello", "aaa", "bbb");
        List<String> resultList = filterStr(list, x -> x.length() > 3);
        resultList.forEach(System.out::println);
    }
    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> stringList = new ArrayList<>();
        for (String str : list) {
            if (pre.test(str)) {
                stringList.add(str);
            }
        }
        return stringList;
    }

}
