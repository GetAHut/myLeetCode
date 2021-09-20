package com.xyt.leecode.solution.September;

/**
 * @Author: xyt
 * @Description: leetCode 673 最长递增子序列的个数  （中等）
 * @Date: 2021/9/20 9:24
 */
public class LeetCode673 {

    /**
     * 给定一个未排序的整数数组，找到最长递增子序列的个数。
     * 示例 1:
     * 输入: [1,3,5,4,7]
     * 输出: 2
     * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
     * 示例 2:
     * 输入: [2,2,2,2,2]
     * 输出: 5
     * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
     * 注意:给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
     */

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length, maxLen = 0, ans = 0;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;  //初始化
            cnt[i] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]){
                    if (dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];    //重置计数
                    } else if (dp[i] == dp[j] + 1){
                        cnt[i] += cnt[j];
                    }
                }
            }

            if (dp[i] > maxLen){
                maxLen = dp[i];
                ans = cnt[i];
            } else if (dp[i] == maxLen){
                ans += cnt[i];
            }
        }
        return ans;
    }
}
