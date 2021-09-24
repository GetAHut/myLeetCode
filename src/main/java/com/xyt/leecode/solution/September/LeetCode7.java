package com.xyt.leecode.solution.September;

/**
 * @Author: xyt
 * @Description: leetCode 7 整数反转
 * @Date: 2021/9/24 17:11
 */
public class LeetCode7 {

    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，就返回 0。
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     * 示例 1：
     *      输入：x = 123
     *      输出：321
     * 示例 2：
     *      输入：x = -123
     *      输出：-321
     */

    /**
     * 循环弹出末尾数字， 即 x % 10 = v;
     *      ans = ans * 10 + v;
     *
     *     因为存在2 ^ 32 上限， Int_Max;
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int ans = 0;
        while (x != 0){
            //判定条件  [−2^31, 2^31− 1]
            if (ans < Integer.MIN_VALUE / 10 || ans > Integer.MAX_VALUE / 10){
                return 0;
            }
            int v = x % 10;
            x /= 10;    //循环
            ans = ans * 10 + v;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(reverse(10));
    }
}
