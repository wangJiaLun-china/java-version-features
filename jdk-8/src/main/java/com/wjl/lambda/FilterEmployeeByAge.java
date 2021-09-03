package com.wjl.lambda;

/**
 * @author wangJiaLun
 * @date 2021-09-03
 **/
public class FilterEmployeeByAge implements MyPredicate<Employee>{

    @Override
    public boolean test(Employee t) {
        return t.getAge() >=35;
    }
}
