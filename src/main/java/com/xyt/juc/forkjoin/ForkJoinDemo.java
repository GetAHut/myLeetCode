package com.xyt.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * 使用forkJoin 求一个整数的和
 *
 * @Author: xyt
 * @Date: 2022/2/8 11:02
 * @version: 1.0.0
 */
public class ForkJoinDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] arr = ArrayUtils.buildArray(100000000);
        // 构建forkJoin任务
        LongSum ls = new LongSum(arr, 0, arr.length);
        // 构建forkJoin
        // 默认CPU核心数
        ForkJoinPool fjp = new ForkJoinPool(12);
        ForkJoinTask<Long> result = fjp.submit(ls);
        System.out.println(result.get());
    }

}
