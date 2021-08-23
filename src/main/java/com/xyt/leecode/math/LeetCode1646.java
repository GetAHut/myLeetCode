package com.xyt.leecode.math;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description: leetCode 获取生成数组中的最大值
 * @Date: 2021/8/23 17:09
 */
public class LeetCode1646 {

    /**
     * 给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：
     *
     * nums[0] = 0
     * nums[1] = 1
     * 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
     * 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
     * 返回生成数组 nums 中的 最大 值。
     * 示例 1：
     *      输入：n = 7
     *      输出：3
     *      解释：根据规则：
     *        nums[0] = 0
     *        nums[1] = 1
     *        nums[(1 * 2) = 2] = nums[1] = 1
     *        nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
     *        nums[(2 * 2) = 4] = nums[2] = 1
     *        nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
     *        nums[(3 * 2) = 6] = nums[3] = 2
     *        nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
     *      因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
     * 示例 2：
     *      输入：n = 2
     *      输出：1
     *      解释：根据规则，nums[0]、nums[1] 和 nums[2] 之中的最大值是 1
     * 示例 3：
     *      输入：n = 3
     *      输出：2
     *      解释：根据规则，nums[0]、nums[1]、nums[2] 和 nums[3] 之中的最大值是 2
     */

    /**
     *
     * @param n
     * @return
     */
    public static int getMaximumGenerated(int n) {

        if (n < 1) return 0;

        int[] nums = new int[n + 1];
        nums[1] = 1;
        int index = 1, len = 2;
        int max = 1;
        while (len <= n + 1){
            if ((2 * index) >= 2 && (2 * index) <= n){
                nums[2 * index] = nums[index];
                max = Math.max(max, nums[2 * index]);
            }
            if ((2 * index + 1) >= 2 && (2 * index + 1) <= n){
                nums[2 * index + 1] = nums[index] + nums[index + 1];
                max = Math.max(max, nums[2 * index + 1]);
            }
            index ++;
            len ++;
        }
        return max;
    }

    /**
     * 官方解题思路：
     *  将求数组公式转换为 nums[i] = nums[i / 2] + i % 2 * nums[i / 2 + 1];
     * @param n
     * @return
     */
    public static int getMaximumGeneratedOfficial(int n){

        if (n == 0) return 0;
        int[] nums = new int[n + 1];
        nums[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            nums[i] = nums[i / 2] + i % 2 * nums[i / 2 + 1];
        }
        return Arrays.stream(nums).max().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(getMaximumGenerated(1));
        System.out.println(getMaximumGeneratedOfficial(7));
    }
}
