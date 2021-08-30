package com.xyt.leecode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @Author: xyt leetCode 528 按权重随机选择(中等)
 * @Description:
 * @Date: 2021/8/30 10:47
 */
public class LeetCode528 {

    /**
     * 给定一个正整数数组w ，其中w[i]代表下标 i的权重（下标从 0 开始），
     * 请写一个函数pickIndex，它可以随机地获取下标 i，选取下标 i的概率与w[i]成正比。
     *
     * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3)= 0.25 （即，25%），
     * 而选取下标 1 的概率为 3 / (1 + 3)= 0.75（即，75%）。
     * 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。
     *
     * 示例 1：
     *      输入：
     *      ["Solution","pickIndex"]
     *      [[[1]],[]]
     *      输出：
     *      [null,0]
     *      解释：
     *      Solution solution = new Solution([1]);
     *      solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
     */

    List<Integer> list;
    public LeetCode528(int[] w) {
        list = new ArrayList<>();
        int index = 0;
        while (index < w.length){   //O(n^2) 超出内存限制！^.^
            int temp = w[index];
            while (temp > 0){
                list.add(index);
                temp --;
            }
            index ++;
        }
    }

    /**
     * 根据随机概率返回值
     *  思路一： 将按照arr[i] 存入将下标存入list中；
     *          list.size 随机数 list.get();
     * @return
     */
    public int pickIndex() {

        Random random = new Random();

        return list.get(random.nextInt(list.size()));
    }

    public static void main(String[] args) {
        int[] arr = {1, 3};
        LeetCode528 test = new LeetCode528(arr);
        System.out.println(test.pickIndex());
        WeightSolution weightSolution = new WeightSolution(arr);
        System.out.println(weightSolution.pickIndex());
    }
}

class WeightSolution{

    int[] pre;
    int weight; //权重

    public WeightSolution(int[] w){
        pre = new int[w.length];
        pre[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            pre[i] = pre[i - 1] + w[i];
        }
        weight = Arrays.stream(w).sum();
    }

    /**
     * 获取二分查询
     * @return
     */
    public int pickIndex() {
        int val = (int)(Math.random() * weight) + 1;
        return binarySearch(val);
    }

    /**
     * 二分查找
     * @param val
     * @return
     */
    private int binarySearch(int val){
        int front = 0, back = pre.length - 1;
        while (front < back){
            int mid = (back - front) / 2 + front;
            if (pre[mid] < val){
                front = mid + 1;
            } else
                back = mid;
        }
        return front;
    }

}
