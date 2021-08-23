package com.xyt.leecode.sort;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description:  选择排序
 * @Date: 2021/8/23 14:53
 */
public class SelectionSort {

    /**
     * 选择排序 A 与B比较取其一A在于C比较
     * @param nums
     */
    public static void sort(int[] nums){
        if (nums.length == 0){
            throw new NullPointerException();
        }

        for (int i = 0; i < nums.length; i++) {     //时间复杂度O(n^2)
            int curr = i;                           //空间复杂度O(n)
            //拿i后面的
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]){
                    curr = j;
                    swap(nums, i, curr);
                }
            }
        }
    }

    /**
     * 交换
     * @param left
     * @param right
     */
    private static void swap(int[] nums, int left, int right){
        nums[left] = nums[left] + nums[right];
        nums[right] = nums[left] - nums[right];
        nums[left] = nums[left] - nums[right];
    }

    public static void main(String[] args) {
        int[] arr = {7, 5, 3, 6, 1, 9, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
