package com.xyt.leecode.sort;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description: 归并排序 (JDK排序源码 以归并排序实现)
 * @Date: 2021/8/20 14:24
 */
public class MergeSort {

    /**
     * 归并排序：
     *      归并排序是 插入排序以及希尔排序的再次递进版；
     *    算法思想：
     *      将数组按照 len/2无限递归拆开， 直到 每一段只有一个元素停止。
     *      并： 将拆开的每一个元素排好序合并。 若合并两段元素个数大于1，
     *      可以通过插入排序的思想合并， 但是因为每一段都是排过序的。所以
     *      可以理解为完美插入排序
     */

    /**
     * 时间复杂度 O(n log n)
     * 归并排序实现， 使用递归和分治的思想
     * @param nums
     * @param left
     * @param right
     */
    public static void sort(int[] nums, int left, int right){
        if (left < right){
            int mid = (left + right) / 2;   //分段
            sort(nums, left, mid);     //左边递归分段
            sort(nums, mid + 1, right);    //右边递归分段
            //合并元素
            merge(nums, left, mid, right);
        }
    }

    /**
     * 对每一段元素合并
     * @param nums
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] nums, int left, int mid, int right){
        int[] temp = new int[nums.length];      //临时数组存储合并元素

        int _left = left;   //左段指针
        int _right = mid + 1;   //  右段指针
        int index = left;   //临时数组下标
        while (_left <= mid && _right <= right){
            //将合并的两段有序数组再次合并是比较
            if (nums[_left] < nums[_right]){
                temp[index] = nums[_left];
                _left ++;
            } else {
                temp[index] = nums[_right];
                _right ++;
            }
            index ++;
        }
        //当比较完成之后， 会残留左边数据和右边的元素 未添加进temp[]
        //以下两个while 只能有一个执行， 不可能同时运行。
        while (_left <= mid){
            temp[index ++] = nums[_left ++];
        }
        while (_right <= right){
            temp[index ++] = nums[_right ++];
        }
        
        //将临时数组元素重新赋值给nums[] 从left开始  到right结束
        for (int i = left; i <= right; i++) {
            nums[i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 9, 3, 7, 1, 6, 2};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
