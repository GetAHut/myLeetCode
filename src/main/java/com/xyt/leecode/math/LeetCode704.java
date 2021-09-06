package com.xyt.leecode.math;

/**
 * @Author: xyt
 * @Description: leetCode 704 二分查找 简单
 * @Date: 2021/9/6 8:35
 */
public class LeetCode704 {

    /**
     * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，
     * 写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
     * 示例 1:
     *
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     * 示例2:
     *
     * 输入: nums = [-1,0,3,5,9,12], target = 2
     * 输出: -1
     * 解释: 2 不存在 nums 中因此返回 -1
     *
     */

    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {

        int low = 0, high = nums.length - 1;

        while (low <= high){
            int mid = (high - low) / 2 + low;
            if (nums[mid] > target){
                high = mid - 1;
            } else if (nums[mid] < target){
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(search(arr, 8));
    }

}
