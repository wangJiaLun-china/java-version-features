package com.wjl.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wangJiaLun
 * @date 2021-09-02
 **/
@Data
@AllArgsConstructor
public class Employee {

    private String name;
    private int age;
    private double salary;

}
