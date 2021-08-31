package com.xyt.leecode.math;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description: leetCode  1109 航班预订统计 （中等）
 * @Date: 2021/8/31 12:05
 */
public class LeetCode1109 {

    /**
     * 这里有n个航班，它们分别从 1 到 n 进行编号。
     *
     * 有一份航班预订表bookings ，表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]
     * 意味着在从 firsti到 lasti （包含 firsti 和 lasti ）的 每个航班
     * 上预订了 seatsi个座位。
     *
     * 请你返回一个长度为 n 的数组answer，其中 answer[i] 是航班 i 上预订的座位总数。
     * 示例 1：
     *      输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
     *      输出：[10,55,45,25,25]
     *      解释：
     *      航班编号        1   2   3   4   5
     *      预订记录 1 ：   10  10
     *      预订记录 2 ：       20  20
     *      预订记录 3 ：       25  25  25  25
     *      总座位数：      10  55  45  25  25
     *      因此，answer = [10,55,45,25,25]
     * 示例 2：
     *      输入：bookings = [[1,2,10],[2,2,15]], n = 2
     *      输出：[10,25]
     *      解释：
     *      航班编号        1   2
     *      预订记录 1 ：   10  10
     *      预订记录 2 ：       15
     *      总座位数：      10  25
     *      因此，answer = [10,25]
     */

    /**
     * 思路一：(暴力解法 ) （超出时间限制）
     *      根据题意，可以从遍历航班编号， 若航班编号存在bookings中
     *      则取出seats i 添加到ans[]中
     * @param bookings
     * @param n
     * @return
     */
    public static int[] corpFlightBookings(int[][] bookings, int n) {

        int[] ans = new int[n];
        while (n > 0){      //O(n^2)
            for (int[] booking : bookings) {
                int first = booking[0], last = booking[1];
                int seats = booking[2];
                if (first <= n && last >= n){
                    ans[n - 1] += seats;
                }
            }
            n --;
        }

        return ans;
    }

    /**
     * 思路二： 差分数组 + 前缀和
     *  差分数组对应的概念是前缀和数组，对于数组 [1,2,2,4][1,2,2,4]，
     *  其差分数组为 [1,1,0,2][1,1,0,2]，差分数组的第 ii 个数即为
     *  原数组的第 i-1i−1 个元素和第 ii 个元素的差值，
     *  也就是说我们对差分数组求前缀和即可得到原数组。
     *
     *  举个例子： 如[2 ,4, 10]，则表明，航班2， 3， 4都预订了10个座位
     *      所以 10只对这三个航班有效， 应用到差分上就是在2上+10， 5上-10
     *
     * @param bookings
     * @param n
     * @return
     */
    public static int[] corpFlightBookingsDiff(int[][] bookings, int n){

        int[] ans = new int[n];
        for (int[] booking : bookings) {
            //差分左边界值
            ans[(booking[0]) - 1] += booking[2];
            if (booking[1] < n)
                //差分右边界值  求出差分数组
                ans[booking[1]] -= booking[2];
        }

        //差分数组求前缀和
        for (int i = 1; i < n; i++) {
            ans[i] += ans[i - 1];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
//        int[] ints = corpFlightBookings(bookings, 5);
        int[] ints = corpFlightBookingsDiff(bookings, 5);
        System.out.println(Arrays.toString(ints));
    }
}
