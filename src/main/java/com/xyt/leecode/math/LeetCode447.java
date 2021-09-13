package com.xyt.leecode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xyt
 * @Description:    leetCode 447 回旋镖的数量 (中等)
 * @Date: 2021/9/13 8:40
 */
public class LeetCode447 {

    /**
     * 给定平面上n 对 互不相同 的点points ，其中 points[i] = [xi, yi] 。回旋镖
     * 是由点(i, j, k) 表示的元组 ，其中i和j之间的距离和i和k之间的距离相等（需要考虑元组的顺序）。
     * 返回平面上所有回旋镖的数量。
     * 示例 1：
     *      输入：points = [[0,0],[1,0],[2,0]]
     *      输出：2
     *      解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
     * 示例 2：
     *      输入：points = [[1,1],[2,2],[3,3]]
     *      输出：2
     * 示例 3：
     *      输入：points = [[1,1]]
     *      输出：0
     */

    /**
     * 使用哈希 + 枚举的方式
     *  由题意 ：
     *      枚举出所有点到point[i]的距离。
     *      然后 设有m 个点  point[i]的距离相等。且需要考虑到顺序的情况下 使用排列组合
     *          到point[i]的回旋镖个数为 m (m - 1)
     *      m 可以通过存放map() key 是dis, value 是个数，存放时dis相同则value递增
     * @param points
     * @return
     */
    public static int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] p : points) {
            //哈希表
            Map<Integer, Integer> maps = new HashMap<>();
            //求欧式距离(直线距离), 曼和顿距离为 坐标点间距离
            for (int[] _p : points) {
                int dis = (p[0] - _p[0]) * (p[0] - _p[0]) +
                        (p[1] - _p[1]) * (p[1] - _p[1]);
                //getOrDefault() 若有这个key值，则使用key， 没有则使用默认值
                maps.put(dis, maps.getOrDefault(dis, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : maps.entrySet()){
                //距离相同点个数
                int m = entry.getValue();
                //丢回旋镖数
                ans += m * (m - 1);
            }
        }
        return ans;
    }
}
