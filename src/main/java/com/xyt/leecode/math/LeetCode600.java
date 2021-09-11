package com.xyt.leecode.math;

/**
 * @Author: xyt
 * @Description: leetCode 600 不含连续1的非负整数
 * @Date: 2021/9/11 15:06
 */
public class LeetCode600 {

    /**
     * 给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含连续的1的个数。
     * 示例 1:
     *      输入: 5
     *      输出: 5
     *      解释:
     *      下面是带有相应二进制表示的非负整数<= 5：
     *      0 : 0
     *      1 : 1
     *      2 : 10
     *      3 : 11
     *      4 : 100
     *      5 : 101
     *      其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。
     * 说明: 1 <= n <= 109
     */

    /**
     * 思路： 动态规划 dp
     *      状态转移方程：dp[i] = dp[i - 1] + dp[i - 2];
     *      满二叉树
     * @param n
     * @return
     */
    public static int findIntegers(int n) {
        //
        int[] dp = new int[31];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 31; i++) {
            //状态转移方程
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int pre = 0, ans = 0;
        for (int i = 29; i >= 0; i--) {
            int temp = 1 << i;  //2的i次方
            if ((n & temp) != 0){
                ans += dp[i + 1];
                if (pre == 1) break;
                pre = 1;
            } else {
                pre = 0;
            }

            if (i == 0){
                ++ ans;
            }
        }
        return ans;
    }
}

class LeetCode600Test{
    public static void main(String[] args) {
        System.out.println(LeetCode600.findIntegers(6));
    }
}
