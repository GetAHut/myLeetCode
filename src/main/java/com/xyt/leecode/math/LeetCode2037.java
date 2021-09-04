package com.xyt.leecode.math;

/**
 * @Author: xyt
 * @Description: leetCode 斐波那契数列 简单
 * @Date: 2021/9/4 12:12
 */
public class LeetCode2037 {

    /**
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。
     * 斐波那契数列的定义如下：
     *
     * F(0) = 0, F(1)= 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
     *
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     *
     * 示例 1：
     *
     * 输入：n = 2
     * 输出：1
     * 示例 2：
     *
     * 输入：n = 5
     * 输出：5
     */


    /**
     * 递归
     *  递归出口： f(1) = 1, f(0) = 0
     *  递归公式： f(n) = f(n - 1) +f(n - 2)
     *
     *  动态规划
     *      状态转移方程 F(N) = F(N - 1) + F(N - 2)
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n <= 2) return n;
        final int MOD = 1000000007;
        int a = 0, b = 1, res = 0;
        for (int i = 2; i <= n; i++) {
            res = a + b;
            //循环赋值
            a = b;
            b = res;
        }
        return res % MOD;
    }


    public static void main(String[] args) {
        System.out.println(fib(5));
    }

}
