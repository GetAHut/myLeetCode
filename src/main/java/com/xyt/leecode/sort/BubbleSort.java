package com.xyt.leecode.sort;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description:  冒泡排序
 * @Date: 2021/8/23 15:30
 */
public class BubbleSort {

    /**
     * 冒泡排序
     *  时间复杂度 O(n^2)
     *  空间复杂度O(n)
     *  交换次数 O(n^2)
     *  稳定排序 （在判断前后大小的时候 不能有=号）
     * @param nums
     */
    public static void sort(int[] nums){

        //外层循环冒泡 最后一个不需要冒泡
        for (int i = 0; i < nums.length - 1; i++) {
            //内层循环 去除外层循环次数  外层循环好一次 则排序好一位
            boolean flag = false;   //冒泡优化
            for (int j = 0; j < nums.length - i - 1; j++) {
                //判断大小 交换
                if (nums[j] > nums[j + 1])
                    swap(nums, j, j + 1);
                    flag = true;
            }

            if (!flag) break;
        }
    }

    /**
     * 使用异或的方式交换位置
     *     temp = a^b
     *     则 b = temp ^ a;
     *        a = temp ^ b;
     * @param nums
     * @param left
     * @param right
     */
    private static void swap(int[] nums, int left, int right){
        nums[left] = nums[left] ^ nums[right];
        nums[right] = nums[left] ^ nums[right];
        nums[left] = nums[left] ^ nums[right];
    }

    public static void main(String[] args) {
        int[] arr = {7, 5, 3, 6, 1, 9, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
