package com.xyt.leecode.sort;

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

        int i = nums.length;    //i 用来表示增量

        while (i > 0){
            //根据i的增量 来跳跃循环（通过插入排序方式）
            for (int j = 1; j < nums.length; j = j + i) {
                int data = nums[j];
                int k = j - 1;
                for ( ; k >= 0 ; k--) {
                    if (nums[k] > data)
                        nums[k + 1] = nums[k];
                    else
                        break;  //通过希尔排序 会有更多的走break，改进的时间复杂度。
                }
                nums[k + 1] = data;
            }
            i = i / 2;  //取整数，到i = 1终止算法。
        }
    }

    public static void main(String[] args) {
        int[] arr = {7, 5, 3, 6, 1, 9, 8};
        sort(arr);
        System.out.println(arr);
    }
}
