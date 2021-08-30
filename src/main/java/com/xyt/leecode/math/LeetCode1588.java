package com.xyt.leecode.math;

/**
 * @Author: xyt
 * @Description: leetCode 1588 所有奇数长度的数组的和 简单
 * @Date: 2021/8/30 8:44
 */
public class LeetCode1588 {

    /**
     * 给你一个正整数数组arr，请你计算所有可能的奇数长度子数组的和。
     * 子数组 定义为原数组中的一个连续子序列。
     * 请你返回 arr中 所有奇数长度子数组的和 。
     * 示例 1：
     *      输入：arr = [1,4,2,5,3]
     *      输出：58
     *      解释：所有奇数长度子数组和它们的和为：
     *      [1] = 1
     *      [4] = 4
     *      [2] = 2
     *      [5] = 5
     *      [3] = 3
     *      [1,4,2] = 7
     *      [4,2,5] = 11
     *      [2,5,3] = 10
     *      [1,4,2,5,3] = 15
     *      我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
     * 示例 2：
     *      输入：arr = [1,2]
     *      输出：3
     *      解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
     * 示例 3：
     *      输入：arr = [10,11,12]
     *      输出：66
     */

    /**
     *
     * @param arr
     * @return
     */
    public static int sumOddLengthSubArrays(int[] arr) {

        int ans = 0;
        if (arr.length == 1) return arr[0];
        if (arr.length == 2) return arr[0] + arr[1];

        int n = 0;
        while (n <= arr.length){    //时间复杂度  O(n^3)
            int index = 0;
            while (index < arr.length){
                if (index + n >= arr.length)
                    break;
                ans += sum(arr, index, index + n);
                index ++;
            }
            if (index + n >= arr.length)
                n += 2;
        }

        return ans;
    }

    /**
     * 求数组部分和
     * @param index
     * @param end
     * @return
     */
    private static int sum(int[] arr, int index, int end){
        int ans = 0;
        while (index <= end){
            ans += arr[index];
            index ++;
        }
        return ans;
    }

    /**
     * 官方解答：
     *  arr[i]在每一个奇数子数组中的出现个数
     *  则结果则为 ans += arr[i] * 个数
     *
     *  问题 怎么求奇数数组中的出现个数
     * @param arr
     * @return
     */
    public static int sumOddLengthSubArraysOfficial(int[] arr){

        int ans = 0;
        for (int i = 0; i < arr.length; i++) { //O(n)
            //定义当前位置左边、右边数组个数
            int left = i, right = arr.length - i - 1;
            int leftOdd = (left + 1) / 2;   //左边奇数个数
            int rightOdd = (right + 1) / 2; //右边奇数个数
            int leftEven = left / 2 + 1;    //左边偶数个数
            int rightEven = right / 2 + 1;  //右边偶数个数
            //leftOdd * rightOdd 包含arr[i]子数组的个数
            ans += arr[i] * (leftOdd * rightOdd + leftEven * rightEven);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 5, 3};
//        int[] arr = {10, 11, 12};
        System.out.println(sumOddLengthSubArrays(arr));
        System.out.println(sumOddLengthSubArraysOfficial(arr));
    }
}
