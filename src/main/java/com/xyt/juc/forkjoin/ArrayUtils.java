package com.xyt.juc.forkjoin;

import java.util.Random;

/**
 * 数组工具类
 *
 * @Author: xyt
 * @Date: 2022/2/8 11:12
 * @version: 1.0.0
 */
public class ArrayUtils {

    public static int[] buildArray(int size){
        int[] arr = new int[size];
        for (int i = 0; i < size - 1; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(1000000);
        }
        return arr;
    }
}
