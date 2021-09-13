package com.xyt.leecode.interview;

/**
 * @Author: xyt
 * @Description: 遍历大量数据测试
 * @Date: 2021/9/13 10:34
 */
public class CirculationTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {

        }
        System.out.println("首次遍历 :" + (System.currentTimeMillis() - start) + "ms");

        long _start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i += 16) {

        }
        System.out.println("二次遍历 :" + (System.currentTimeMillis() - _start) + "ms");
    }
}
