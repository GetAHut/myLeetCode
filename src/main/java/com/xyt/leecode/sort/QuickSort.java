package com.xyt.leecode.sort;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description: 快速排序
 * @Date: 2021/8/23 16:06
 */
public class QuickSort {

    /**
     * 快速排序  快排
     *  时间复杂度：O(nlogn)
     *  空间复杂度： O(n)
     *  不稳定
     *
     *      优化：思路 就是基准数的位置  （1. 取三个数中间的数）
     * @param nums
     * @param left
     * @param right
     */
    public static void sort(int[] nums, int left, int right){

        if (left > right) return;

        int _base = nums[left];     // 基准数：取数列第一个
        int front = left, back = right; //定义前后之指针

        while (front < back){
            //第一步 从后往前遍历 找比基准数小的值 交换
            while (front < back && _base <= nums[back]){
                back --;   //未找到则往前移动
            }
            if (front < back){     //表示已经找到了可用于交换的值
                swap(nums, front, back);   //交换
                front ++;   //交换结束 前指针移动
            }
            //第二步 从前往后遍历。 找到比基准数大的 交换
            while (front < back && _base >= nums[left]){
                front ++;   //未找到则前指针向后移动
            }
            if (front < back){ //通过while条件判断  此条件成立则一定找到了
                swap(nums, front, back);
                back --;   //交换结束 后指针向前移动
            }
        }
        //结局 都会找到front = right
        //重复则操作，数列被基准数分割成三部分， 左右部分 尾递归！
        if (left < front)
            sort(nums, left, front - 1);
        if (front < right)
            sort(nums, front + 1, right);

    }

    /**
     * 数组交换
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
        sort(arr, 0, 6);
        System.out.println(Arrays.toString(arr));
    }
}
