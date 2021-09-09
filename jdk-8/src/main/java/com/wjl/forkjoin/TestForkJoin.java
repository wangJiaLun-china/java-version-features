package com.wjl.forkjoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author wangJiaLun
 * @date 2021-09-09
 **/
public class TestForkJoin {

    /**
     *  ForkJoin 框架
     */
    @Test
    public void test(){
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 100000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }
    // 数据量大或者单个处理耗时较长时 使用 ForkJoin才划算, 因为拆分也需要耗时
    /**
     *  普通 for
     */
    @Test
    public void test2(){
        Instant start = Instant.now();
        long sum = 0L;
        for (long i = 0; i < 100000000L; i++) {
            sum += i;
        }
        Instant end = Instant.now();
        System.out.println(sum);
        System.out.println(Duration.between(start, end).toMillis());
    }

    /**
     *  java8 并行流
     */
    @Test
    public void test3(){
        Instant start = Instant.now();
        Long sum = LongStream.range(0, 100000000L)
                .parallel()
                .reduce(0, Long::sum);
        Instant end = Instant.now();
        System.out.println(sum);
        System.out.println(Duration.between(start, end).toMillis());
    }
}
