package com.xyt.leecode.solution.September;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description: leetCode 371 两整数之和
 * @Date: 2021/9/26 8:54
 */
public class LeetCode371 {

    /**
     * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
     */

    /**
     * 不能使用 + 号
     * @param a
     * @param b
     * @return
     */
    public static int getSum(int a, int b) {
        while (b != 0){
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(getSum(1, 2));
    }
}
