package com.xyt.leecode.solution.September;

/**
 * @Author: xyt
 * @Description: leetCode 223矩形面积  中等
 * @Date: 2021/9/30 9:00
 */
public class LeeetCode223 {

    /**
     * 给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。
     *
     * 每个矩形由其 左下 顶点和 右上 顶点坐标表示：
     *
     * 第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
     * 第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
     * 示例 见
     * @link https://leetcode-cn.com/problems/rectangle-area/
     */

    /**
     * 思路 给出两顶点； 坐标相减 再* 则是面积。
     * 两个图形， 判断，顶点是否在一个图形内部， 则有重合面积。
     *  判断顶点是否在内部：
     *
     * @return
     */
    public static int computeArea(int ax1, int ay1, int ax2, int ay2,
                                  int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1), area2 = (bx2 - bx1) * (by2 - by1);
        int overWidth = Math.min(ax2, bx2) - Math.max(ax1, bx1),
                overHigh = Math.min(ay2, by2) - Math.max(ay1, by1);
        int overArea = Math.max(overHigh, 0) * Math.max(overWidth, 0);

        return area1 + area2 - overArea;
    }
}
