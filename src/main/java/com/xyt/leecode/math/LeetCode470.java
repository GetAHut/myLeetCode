package com.xyt.leecode.math;

/**
 * @Author: xyt
 * @Description: leetCode 470  用 Rand7() 实现 Rand10() 中等
 * @Date: 2021/9/5 10:20
 */
public class LeetCode470 {

    /**
     * 已有方法rand7可生成 1 到 7 范围内的均匀随机整数，试写一个方法rand10生成 1 到 10 范围内的均匀随机整数。
     *
     * 不要使用系统的Math.random()方法。
     * 示例 1:
     *      输入: 1
     *      输出: [7]
     * 示例 2:
     *      输入: 2
     *      输出: [8,4]
     * 示例 3:
     *      输入: 3
     *      输出: [8,1,10]
     */

    public int rand10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = col + (row - 1) * 7;
        } while (idx > 40);
        return 1 + (idx - 1) % 10;
    }

    private int rand7(){
        //题目已实现
        return 0;
    }
}
