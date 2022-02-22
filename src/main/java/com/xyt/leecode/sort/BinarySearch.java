package com.xyt.leecode.sort;

/**
 * @Author: xyt
 * @Description: 二分查找
 * @Date: 2021/9/15 9:36
 */
public class BinarySearch {

    /**
     * 二分查找 基础
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target){
        int low = 0, high = nums.length - 1;
        while (low <= high){
            int mid = (high - low) / 2 + low;
            if (nums[mid] > target){
                high = mid - 1;     //左边查找
            } else if (nums[mid] < target){
                low = mid + 1;      //右边查找
            } else {
                return mid;
            }
        }
        return -1;
    }
}
