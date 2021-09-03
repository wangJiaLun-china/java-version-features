package com.wjl.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 *  一. Lambda 表达式的基础语法: Java8 中引入了操作符"->", 称为箭头操作符 或 Lambda 操作符
 *                                              箭头操作符将Lambda 表达式拆分为两部分:
 *  左侧 : Lambda 表达式的参数列表
 *  右侧: Lambda 表达式中所需要执行的功能, 即 Lambda 体
 *
 *  二. Lambda 表达式需要"函数式接口"支持
 *  函数式接口: 接口中只有一个抽象方法的接口, 称为函数式接口. 可以使用 @FunctionalInterface 检查
 *
 * @author wangJiaLun
 * @date 2021-09-03
 **/
public class TestLambda2 {

    /**
     * 语法格式一: 无参数, 无返回值
     *      ( ) -> System.out.printLn("hello world!");
     */
    @Test
    public void test1(){
        int num = 0;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world" + num);
            }
        };
        runnable.run();
        System.out.println("-------------------");

        Runnable runnable1 = ()->System.out.println("hello world"+ num);
        runnable1.run();
    }

    /**
     * 语法格式二: 单个参数, 无返回值
     *      (s) -> System.out.printLn(s);
     * 语法格式三: 只有单个参数小括号可以省略不写
     *      s -> System.out.printLn(s);
     */
    @Test
    public void test2(){
        Consumer<String> con = (s) -> System.out.println(s);
        con.accept("hello world");
    }

    /**
     * 语法格式四: 有多个参数, 有返回值, 需要处理多条语句
     *    Comparator<Integer> com = (o1, o2) -> {
     *       System.out.println("函数式接口");
     *       return Integer.compare(o1, o2);
     *   };
     */
    @Test
    public void test3(){
        Comparator<Integer> com = (o1, o2) -> {
            System.out.println("函数式接口");
            return Integer.compare(o1, o2);
        };
    }

    /**
     * 语法格式五: 若 Lambda中只有一条语句, return和大括号都可以省略
     *      Comparator<Integer> com = (o1, o2) ->Integer.compare(o1, o2);
     * 语法格式六: Lambda 表达式的参数列表的数据类型可以省略不写, 因为JVM编译器通过上下文推断出数据类型即 "类型推断"
     *   (Integer o1, Integer o2) ->Integer.compare(o1, o2);
     */
    @Test
    public void test4(){
        Comparator<Integer> com = (o1, o2) ->Integer.compare(o1, o2);
    }

    // 需求: 对一个数进行运算
    @Test
    public void test5(){
        Integer num = operation(100, (x) -> x * x);
        System.out.println(num);
        System.out.println(operation(200, x -> x+200));
    }

    public Integer operation(Integer num, MyFun mf){
        return mf.getValue(num);
    }


}
