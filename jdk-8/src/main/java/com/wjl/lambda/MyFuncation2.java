package com.wjl.lambda;

/**
 * @author wangJiaLun
 * @date 2021-09-06
 **/
@FunctionalInterface
public interface MyFuncation2<T, R> {

    R getValue(T t1, T t2);
}
