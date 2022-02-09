package com.xyt.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @Author: xyt
 * @Date: 2022/2/8 11:03
 * @version: 1.0.0
 */
public class LongSum extends RecursiveTask<Long> {

    /** 拆分最小阈值 */
    private final int SEQUENTIAL_THRESHOLD = 100000;

    int low, high;
    int[] array;

    public LongSum(int[] arr, int low, int high){
        this.array = arr;
        this.low = low;
        this.high = high;
    }

    /**
     * 用以处理任务
     * @return
     */
    @Override
    protected Long compute() {

        // 当拆分的任务区间小于阈值 则直接累加求和
        if (high - low < SEQUENTIAL_THRESHOLD){
            long sum = 0;
            for (int i = low; i < high; i++) {
                sum += array[i];
            }
            return sum;
        } else { // 任务过大则继续拆分
            // 二分
            int mid = (high - low) / 2 + low;
            LongSum left = new LongSum(array, low, mid);
            LongSum right = new LongSum(array, mid, high);
            // 提交任务
            left.fork();
            right.fork();
            // 获取任务的执行结果， 将阻塞当前线程直到对应的子任务完成运行并返回结果
            Long leftAns = left.join();
            Long rightAns = right.join();
            return leftAns + rightAns;
        }
    }
}
