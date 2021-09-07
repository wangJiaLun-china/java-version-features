package com.wjl.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangJiaLun
 * @date 2021-09-02
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private String name;
    private int age;
    private double salary;

    public Employee(int age) {
        this.age = age;
    }
}
