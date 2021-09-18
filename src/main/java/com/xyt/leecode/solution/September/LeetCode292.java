package com.xyt.leecode.solution.September;

/**
 * @Author: xyt
 * @Description: leetCode 292 Nim 游戏 （简单）
 * @Date: 2021/9/18 8:35
 */
public class LeetCode292 {

    /**
     * 你和你的朋友，两个人一起玩Nim 游戏：
     *
     * 桌子上有一堆石头。
     * 你们轮流进行自己的回合，你作为先手。
     * 每一回合，轮到的人拿掉1 - 3 块石头。
     * 拿掉最后一块石头的人就是获胜者。
     * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n
     * 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
     * 示例 1：
     *      输入：n = 4
     *      输出：false
     *      解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；
     *          因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
     * 示例 2：
     *      输入：n = 1
     *      输出：true
     *
     */

    /**
     * 当只剩下4块石头的时候， 那么你一定会输掉。
     * 二当剩下8快的时候， 对手都可以拿走几块 给你再次造成4块剩余是你输掉比赛。
     * 所以 只要是4*N 则你一定输掉比赛
     * @param n
     * @return
     */
    public static boolean canWinNim(int n) {

        return n % 4 != 0;
    }
}
