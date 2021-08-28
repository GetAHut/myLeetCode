package com.xyt.leecode.math;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description: leetCode 1480 一维数组的动态和 （简单）
 * @Date: 2021/8/28 10:06
 */
public class LeetCode1480 {

    /**
     * 给你一个数组 nums 。数组「动态和」的计算公式为：
     *      runningSum[i] = sum(nums[0]…nums[i]) 。
     * 请返回 nums 的动态和。
     * 示例 1：
     *      输入：nums = [1,2,3,4]
     *      输出：[1,3,6,10]
     *      解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
     * 示例 2：
     *      输入：nums = [1,1,1,1,1]
     *      输出：[1,2,3,4,5]
     *      解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
     * 示例 3：
     *      输入：nums = [3,1,2,10,1]
     *      输出：[3,4,6,16,17]
     */

    /**
     * 动态和数组与原数组长度一致
     * @param nums
     * @return
     */
    public static int[] runningSum(int[] nums) {

        int[] ans = new int[nums.length];
        int index = 1;
        ans[0] = nums[0];
        while (index < nums.length){
            ans[index] = ans[index - 1] + nums[index];
            index ++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(runningSum(nums)));
    }
}
