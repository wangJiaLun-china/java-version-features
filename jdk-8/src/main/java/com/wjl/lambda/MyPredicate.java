package com.wjl.lambda;

/**
 * @author wangJiaLun
 * @date 2021-09-03
 **/
@FunctionalInterface
public interface MyPredicate<T> {

    boolean test(Employee t);
}
