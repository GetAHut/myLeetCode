package com.xyt.leecode.arithmetic;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description:    动态规划算法 (经典例子 背包问题)
 * @Date: 2021/8/24 11:22
 */
public class Dp {

    /**
     * 背包问题描述：
     *      加入小名去商店偷东西， 他带的背包可以装50kg， 商店有3中物品 分别为
     *      1:10kg 60元， 2:20kg， 100元， 3： 30kg， 120元。
     *      求最优装法
     *
     *
     *      根据状态转移方程  price[i - 1] + dp[i - 1][j - weight[i - 1]],
     *                     dp[i - 1][j]
     * @param args
     */
    public static void main(String[] args) {
        int price[] = {6, 10, 12};   //价格
        int weight[] = {1, 2, 3};    //重量

        int _weight = 5, n = 3;
        //动态规划
        int[][] dp = new int[n + 1][_weight + 1];

        //外层 商品个数
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= _weight; j++) {    //背包重量
                if (weight[i - 1] <= j)  //物品重量比背包小 则可放入
                    dp[i][j] = Math.max(
                            //状态转移方程 与 i-1比较，取最大值
                            price[i - 1] + dp[i - 1][j - weight[i - 1]],
                            dp[i - 1][j]
                    );
                else //不能装入  则放上一重量
                    dp[i][j] = dp[i - 1][j];
             }
        }
        for (int i = 0; i < dp.length; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(dp[n][_weight]);
    }
}
