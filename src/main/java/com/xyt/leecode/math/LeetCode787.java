package com.xyt.leecode.math;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description: leetCode  787 k站中转内最便宜的航班 (中等)
 * @Date: 2021/8/24 12:23
 */
public class LeetCode787 {

    /**
     * 有 n 个城市通过一些航班连接。给你一个数组flights ，
     * 其中flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，
     * 以价格 toi 抵达 pricei。
     * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
     * 你的任务是找到出一条最多经过 k站中转的路线，使得从 src 到 dst 的 价格最便宜 ，
     * 并返回该价格。 如果不存在这样的路线，则输出 -1。
     *
     * 示例 1：
     *      输入:
     *      n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     *      src = 0, dst = 2, k = 1
     *      输出: 200
     *
     * 示例 2：
     *      输入:
     *      n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     *      src = 0, dst = 2, k = 0
     *      输出: 500
     */

    /**
     *  思路： 求价格最便宜  可否考虑 贪心算法 或者动态规划
     *      动态规划：  枚举出最后一次航班的起点， 那么前t - 1航班的最小花费
     *      为f(t - 1)[j] 加上最后一次航班的花费cost(j, i)的最小值
     *          状态转移方程：int[][] price = new int[k + 2][n]
     *                  price[i][j] = Math.min(price[i - 1][j] + flights[i][2],
     *                  price[i][j])
     * @param n 城市
     * @param flights 航班数
     * @param src   出发地
     * @param dst   目的地
     * @param k     最多中转k站
     * @return
     */
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int INF = 10000 * 101 + 1;
        int[][] price = new int[k + 2][n];  //最终会飞 k + 1次航班
        for (int i = 0; i < k + 2; i++) {
            Arrays.fill(price[i], INF);
        }

        price[0][src] = 0;  //设置初始 起始地价格
        for (int i = 1; i <= k + 1; i++) {
            for (int[] flight : flights) {
                price[i][flight[1]] = Math.min(
                        price[i - 1][flight[0]] + flight[2],    //本次航班花费  price[i - 1][flight[0]]上一次花费
                        price[i][flight[1]]
                );
            }
        }

        int ans = INF;
        for (int i = 1; i <= k + 1; i++) {
            ans = Math.min(ans, price[i][dst]);
        }
        return ans == INF ? -1 : ans;
    }
}
