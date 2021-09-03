package com.xyt.leecode.math;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author: xyt
 * @Description: leetCode 面试题17.14 最小k个数 （中等）
 * @Date: 2021/9/3 8:51
 */
public class LeetCode2321 {

    /**
     * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
     * 示例：
     *      输入： arr = [1,3,5,7,2,4,6,8], k = 4
     *      输出： [1,2,3,4]
     */

    /**
     * 使用快速排序
     * @param arr
     * @param k
     * @return
     */
    public static int[] smallestK(int[] arr, int k) {
        int[] ans = new int[k];
        quickSort(arr, 0, arr.length - 1);

        while (k > 0){
            ans[k - 1] = arr[k - 1];
            k--;
        }
        return ans;
    }

    /**
     * 使用jdk既定的排序算法
     * @param arr
     * @param k
     * @return
     */
    public static int[] smallestKSort(int[] arr, int k) {
        int[] ans = new int[k];

        Arrays.sort(arr);

        while (k > 0){
            ans[k - 1] = arr[k - 1];
            k--;
        }
        return ans;
    }

    /**
     * 快速排序
     * @param nums
     * @param left
     * @param right
     */
    private static void quickSort(int[] nums, int left, int right){
        if (left > right) return;

        //选取基准数
        int _base = nums[left];
        //前后指针
        int front = left, back = right;
        while (front < back){
            //第一步  从后遍历 与基准数比较 小的交换
            while (front < back && _base <= nums[back]){
                back --;
            }
            if (front < back){
                swap(nums, front, back);
                front ++;
            }
            //第二步 从前遍历，找到比基准数大的 交换
            while (front < back && _base >= nums[front]){
                front ++;
            }
            if (front < back){
                swap(nums, front, back);
                back --;
            }
        }

        //都会执行到front = back
        if (left < front){
            quickSort(nums, left, front - 1);
        }
        if (front < right){
            quickSort(nums, front + 1, right);
        }
    }


    /**
     * 优化后快排
     * @param arr
     * @param k
     * @return
     */
    public static int[] smallestKQuickSort(int[] arr, int k) {
        int[] ans = new int[k];

        quickSort(arr, 0, arr.length - 1, k);

        while (k > 0){
            ans[k - 1] = arr[k - 1];
            k--;
        }
        return ans;
    }


    /**
     * 针对这道题目 快速排序优化  通过给定的k做判断
     *      将只需要处理一边，第一次划分出数组的[left, right] 的位置，使得前
     *      k个数在数组的左侧，；使用 _base表示在基准数在数组中的位置
     *      1. 若_base == k  则直接返回
     *      2. 若_base - left + 1 > k  在表示第k小的数在左侧， 左侧递归
     *      3. 若_base - left + 1 < k  在表示第k个小的数在右侧，右侧递归
     * @param nums
     * @param left
     * @param right
     * @param k
     */
    private static void quickSort(int[] nums, int left, int right, int k){
        if (left >= right) return;

        int _base = randomPartition(nums, left, right);
        int num = _base -left + 1;
        if (k == num)
            return;
        else if (k < num)
            quickSort(nums, left, _base - 1, k);
        else if (k > num)
            quickSort(nums, _base + 1, right, k - num);
    }

    /**
     * 基于随机数划分 基准数
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private static int randomPartition(int[] nums, int left, int right){
        int i = new Random().nextInt(right - left + 1) + left;
        swap(nums, right, i);
        return partition(nums, left, right);
    }

    private static int partition(int[] nums, int left, int right){
        int _base = nums[right];
        int i = left - 1;
        for (int j = 1; j <= right - 1; j++) {
            if (nums[j] <= _base){
                i += 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] nums, int left, int right){
        nums[left] = nums[left] ^ nums[right];
        nums[right] = nums[left] ^ nums[right];
        nums[left] = nums[left] ^ nums[right];
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
//        System.out.println(Arrays.toString(smallestK(arr, 4)));
        System.out.println(Arrays.toString(smallestKQuickSort(arr, 4)));
//        System.out.println(Arrays.toString(smallestKSort(arr, 4)));
    }
}
