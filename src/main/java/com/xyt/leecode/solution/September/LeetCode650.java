package com.xyt.leecode.solution.September;

/**
 * @Author: xyt
 * @Description: leetCode 650 只有两个键的键盘 (中等)
 * @Date: 2021/9/19 12:54
 */
public class LeetCode650 {

    /**
     * 最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
     * Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
     * Paste（粘贴）：粘贴 上一次 复制的字符。
     * 给你一个数字n ，你需要使用最少的操作次数，在记事本上输出 恰好n个 'A' 。
     * 返回能够打印出n个 'A' 的最少操作次数。
     * 示例 1：
     *      输入：3
     *      输出：3
     *      解释：
     *      最初, 只有一个字符 'A'。
     *      第 1 步, 使用 Copy All 操作。
     *      第 2 步, 使用 Paste 操作来获得 'AA'。
     *      第 3 步, 使用 Paste 操作来获得 'AAA'。
     * 示例 2：
     *      输入：n = 1
     *      输出：0
     */

    /**
     * 思路： 当复制粘贴到一半 那么另一半 直接复制粘贴就好 ans = (n/2 + 2); n/2 = n/2/2 + 2
     *      ....以此类推
     *      ans = (n/(2 * x) + 2*x) x为二分次数
     *
     *     方法一 分解质因数
     * @param n
     * @return
     */
    public static int minSteps(int n) {

        int ans = 0;
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0){
                n /= i;
                ans += i;
            }
        }
        if (n > 1){
            ans += n;
        }
        return ans;
    }

    /**
     * 思路二 使用动态规划
     *  必须是使用j个A全部复制， 在经过 多次粘贴 得到i个A结果。
     *      其中 j必须是i的因素、因为粘贴的次数是 i / j - 1，复制次数为1
     *      所以动态规划方程： f(i) = min(f(j) + i / j - 1 + 1)
     * @param n
     * @return
     */
    public static int minStepsDp(int n) {
        int[] f = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0){
                    f[i] = Math.min(f[i], i / j);
                    f[i] = Math.min(f[i], f[i / j] + j);
                }
            }
        }
        return f[n];
    }
}

class LeetCode650Test{
    public static void main(String[] args) {
        System.out.println(LeetCode650.minSteps(2));
    }
}
