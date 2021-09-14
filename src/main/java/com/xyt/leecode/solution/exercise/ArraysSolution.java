package com.xyt.leecode.solution.exercise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xyt
 * @Description: 数组
 * @Date: 2021/9/13 16:28
 */
public class ArraysSolution {

    /**
     * 数组相对排序： arr1和 arr2 ，2数组数不重复，
     *     arr1根据arr2数据排序，若未在arr2中出现，则按照升序放在最后
     * 示例：
     *      输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
     *      输出：[2,2,2,1,4,3,3,9,6,7,19]
     *
     *   思路计数排序。 年龄问题 数组存放hash
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {

        //hash数组
        int index = 0;
        //题目中提示 arr1[i],arr2[1] < 1000，则以数组计数排序
        int[] hash = new int[1001];
        //遍历arr1 将arr1放入hash
        for (int i : arr1) {
            //重复则 + 1
            hash[i] ++;
        }

        //计算 在arr2中的。
        for (int i : arr2) {
            while (hash[i] > 0){
                arr1[index ++] = i;
                hash[i] --;
            }
        }

        //不在arr2中的 也就是hash中大于0的
        for (int i = 0; i < hash.length; i++) {
            while (hash[i] > 0){
                arr1[index ++] = i;
                hash[i] --;
            }
        }
        System.out.println(Arrays.toString(arr1));
        return arr1;
    }

    /**
     *  求任意一个数组中重复的数字
     *  示例 1：
     *      输入：
     *      [2, 3, 1, 0, 2, 5, 3]
     *      输出：2 或 3
     *
     *   思路： 1. 数组如果短可以使用计数方法
     *         2. 数组长 暴力解法 双重遍历；效率低
     *               使用bitmap 来存放
     *         3. 使用set ， 如果存在则直接返回
     *              <code>findRepeatNumberSet()</>
     * @param nums
     * @return
     */
    public static int findRepeatNumber(int[] nums) {
        //因为提示给出限制 每个数都在0 ~ n-1范围内
        //2 <= n <= 100000
        int[] bits = new int[(nums.length >> 5) + 1];

        for (int i = 0; i < nums.length; i++) {
            //bits下标
            int bitIndex = nums[i] >> 5;
            int loc = nums[i] & (32 - 1);
            //当前位置与1 做&运算， 如果是1 则返回i
            int flag = bits[bitIndex] & (1 << loc);
            if (flag == 0){
                bits[bitIndex] |= 1 << loc;
            }else {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 求任意一个数组中重复的数字
     * 使用set
     * @param nums
     * @return
     */
    public static int findRepeatNumberSet(int[] nums){

        Set<Integer> set = new HashSet<>();
        int ans = -1;
        for (int i = 0; i < nums.length; i++) {
//            if (!set.contains(nums[i])){
//                set.add(nums[i]);
//            } else {
//                ans =  nums[i];
//                break;
//            }
            //set.add() return boolean
            if (set.add(nums[i])){
                ans = nums[i];
                break;
            }
        }
        return ans;
    }

    /**
     * 构建乘积数组 leetCode 剑指offer 66、 2120
     *  思路：（不能使用除法）
     *      1. 暴力解法 遍历 *=
     *      2.
     * @link https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/
     * @param a
     * @return
     */
    public static int[] constructArr(int[] a) {
        if (a.length == 0) return new int[a.length];
        int[] ans = new int[a.length];
        ans[0] = 1;
        int tem = 1;
        for (int i = 1; i < a.length; i++) {
            ans[i] = ans[i - 1] * a[i - 1];
        }

        //第二次遍历 倒叙求乘积
        for (int i = a.length - 2; i >= 0 ; i--) {
            tem *= a[i + 1];
            ans[i] *= tem;
        }

        System.out.println(Arrays.toString(ans));
        return ans;
    }

    /**
     * leetCode 4 寻找两个正序数组的中位数
     *      给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
     *      请你找出并返回这两个正序数组的 中位数 。
     *
     *  思路：合并数组， 奇数 返回 len / 2  + 1;偶数 (len / 2 + len / 2 + 1) / 2
     *      合并数组，因为两个是正序数组。
     *
     * @link https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //合并数组， 可否使用贪心算法来解
        // 假设第二个数组的值都大于第一个数组的值。那么直接插入第一个数组的最后就行。
        // 且中位数就是 两个数组长度相加的中位数
        // 如果排在前面的数组，其中有n个数大于 中位数， 则中位数向后移n个位置、
        double ans = 0.0;
        int n = nums1.length, m = nums2.length;
        int len = n + m;
        int[] temp = new int[len];
        int index = 0, front = 0, back = 0;
        while (index < n){
            temp[index ++] = nums1[front ++];
        }
        while (back < m){
            temp[index ++] = nums2[back ++];
        }
        Arrays.sort(temp);

        int k = (len / 2);
        if (len % 2 == 0){
            ans = (double) (temp[k - 1] + temp[k]) / 2;
        } else {
            ans = temp[k];
        }

        return ans;
    }


    /**
     * 数组交换
     * @param data
     * @param left
     * @param right
     */
    private static void swap(int[] data, int left, int right){
        if (left > right) return;
        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;
    }
}

class SolutionTest{

    public static void main(String[] args) {

//        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
//        int[] arr2 = {2, 1, 4, 3, 9, 6};
//        int[] arr1 = {33,22,48,4,39,36,41,47,15,45};
//        int[] arr2 = {22,33,48,4};
//        ArraysSolution.relativeSortArray(arr1, arr2);
//
//        int[] arr = {2, 3, 1, 0, 2, 5, 3};
//        System.out.println(ArraysSolution.findRepeatNumber(arr));

        int[] nums = {};
        ArraysSolution.constructArr(nums);

        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        ArraysSolution.findMedianSortedArrays(nums1, nums2);
    }
}
