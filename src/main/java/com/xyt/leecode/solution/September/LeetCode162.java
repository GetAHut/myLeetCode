package com.xyt.leecode.solution.September;

/**
 * @Author: xyt
 * @Description: leetCode  162 寻找峰值（中等） 要求时间复杂度O(logN)
 * @Date: 2021/9/15 8:36
 */
public class LeetCode162 {

    /**
     * 峰值元素是指其值严格大于左右相邻值的元素。
     * 给你一个整数数组nums，找到峰值元素并返回其索引。
     * 数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
     * 你可以假设nums[-1] = nums[n] = -∞ 。
     * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
     * 示例 1：
     *      输入：nums = [1,2,3,1]
     *      输出：2
     *      解释：3 是峰值元素，你的函数应该返回其索引 2。
     * 示例2：
     *      输入：nums = [1,2,1,3,5,6,4]
     *      输出：1 或 5
     *      解释：你的函数可以返回索引 1，其峰值元素为 2；
     *          或者返回索引 5， 其峰值元素为 6。
     */

    /**
     * 要求时间复杂度在O(logN)， 首先想到二分查找。
     * @param nums
     * @return
     */
    public static int findPeakElement(int[] nums) {
        int ans = -1, len = nums.length;
        int left = 0, right = len - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (compare(nums, mid - 1, mid) < 0
                    && compare(nums, mid, mid + 1) > 0){
                ans = mid;
                break;
            }
            if (compare(nums, mid, mid + 1) < 0){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 辅助函数，处理越界情况
     * @param nums
     * @param index
     * @return
     */
    private static int[] getArr(int[] nums, int index){
        if (index == -1 || index == nums.length){
            return new int[]{0, 0};
        }
        return new int[]{1, nums[index]};
    }

    private static int compare(int[] nums, int front, int back){
        int[] arr1 = getArr(nums, front);
        int[] arr2 = getArr(nums, back);
        if (arr1[0] != arr2[0]){
            return arr1[0] > arr2[0] ? 1 : -1;
        }
        if (arr1[1] == arr2[1]){
            return 0;
        }
        return arr1[1] > arr2[1] ? 1 : -1;
    }
}
