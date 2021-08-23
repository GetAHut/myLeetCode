package com.xyt.leecode.sort;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description: 希尔排序
 * @Date: 2021/8/20 13:38
 */
public class ShellSort {

    /**
     * 希尔排序：
     *      希尔排序是插入排序的一个优化版，递进；
     *      它是将需要排序的数组 ，根据下标增量分成很多段，
     *      每段分别排序，直到最好下标增量为1时，则是整个数组排序，
     *      但是其中有很多段已经排好序了 ，所以应用在插入排序中 break；
     *      当增量为1的段排好序 则算法终止。
     */

    /**
     * 希尔排序实现
     * @param nums
     */
    public static void sort(int[] nums){
        if (nums.length == 0)
            throw new NullPointerException();

        int i = nums.length / 2;    //i 用来表示增量

        while (i >= 1){
            //根据i的增量 来跳跃循环（通过插入排序方式）
            for (int j = i; j < nums.length; j ++) {
                int data = nums[j];
                int k = j - i;
                //根据增量处理
                for ( ; k >= 0 ; k -= i) {
                    if (nums[k] > data)
                        nums[k + i] = nums[k];
                    else
                        break;  //通过希尔排序 会有更多的走break，改进的时间复杂度。
                }
                nums[k + i] = data;
            }
            i /= 2;  //取整数，到i = 1终止算法。
        }
    }

    public static void main(String[] args) {
        int[] arr = {7, 5, 3, 6, 1, 9, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
