package com.wjl.lambda;

import org.junit.Test;

import java.util.*;

/**
 * @author wangJiaLun
 * @date 2021-09-02
 **/
public class TestLambda {


    /**
     *  之前的匿名内部类
     */
    @Test
    public void test1(){
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    /**
     *  lambda 表达式
     */
    public void test2(){
        Comparator<Integer> com = ((o1, o2) -> Integer.compare(o1, o2));
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("zhangsan", 18, 999),
            new Employee("lisi", 38, 1299),
            new Employee("wangwu", 23, 299),
            new Employee("cs1", 36, 12.49),
            new Employee("cs2", 11, 3199)
    );

    // 需求: 获取当前公司中员工年龄大于35的员工信息
    @Test
    public void test3(){
        List<Employee> list = filterEmployees(employees);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployees(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }
        return emps;
    }

    // 需求: 获取当前公司中员工工资大于5000的员工信息
    public List<Employee> filterEmployees2(List<Employee> list){
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getSalary() >= 5000) {
                emps.add(emp);
            }
        }
        return emps;
    }

    // 优化方式一: 策略设计模式
    @Test
    public void test4(){
        List<Employee> employees = filterEmployee(this.employees, new FilterEmployeeByAge());
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        System.out.println("-----------------------");
        List<Employee> employees2 = filterEmployee(this.employees, new FilterEmployeeBySalary());
        for (Employee employee : employees2) {
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp){
        List<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if (mp.test(employee)){
                emps.add(employee);
            }
        }
        return emps;
    }

    // 优化方式二: 匿名内部类
    @Test
    public void test5(){
        List<Employee> employees = filterEmployee(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee t) {
                return t.getSalary() <= 5000;
            }
        });
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    // 优化方式三: Lambda 表达式
    @Test
    public void test6(){
        List<Employee> list = filterEmployee(employees, (e) -> e.getSalary() >= 300);
        list.forEach(System.out::println);
    }

    // 优化方式四: Stream API
    @Test
    public void test7(){
        employees.stream()
                .filter((e) -> e.getSalary() >= 300)
                .forEach(System.out::println);
        System.out.println("---------------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}
