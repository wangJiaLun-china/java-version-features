package com.wjl.lambda;

/**
 * @author wangJiaLun
 * @date 2021-09-03
 **/
public class FilterEmployeeBySalary implements MyPredicate<Employee>{

    @Override
    public boolean test(Employee t) {
        return t.getSalary() >=300;
    }
}
