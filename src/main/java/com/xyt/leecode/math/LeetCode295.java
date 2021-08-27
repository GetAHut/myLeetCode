package com.xyt.leecode.math;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author: xyt
 * @Description:  leetCode 295数据流中的中位数  （困难）
 * @Date: 2021/8/27 9:08
 */
public class LeetCode295 {

    /**
     * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
     * 例如，
     *      [2,3,4]的中位数是 3
     *      [2,3] 的中位数是 (2 + 3) / 2 = 2.5
     *      设计一个支持以下两种操作的数据结构：
     *      void addNum(int num) - 从数据流中添加一个整数到数据结构中。
     *      double findMedian() - 返回目前所有元素的中位数。
     * 示例：
     *      addNum(1)
     *      addNum(2)
     *      findMedian() -> 1.5
     *      addNum(3)
     *      findMedian() -> 2
     *
     * 进阶:
     *      如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
     *      如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
     */
    public static void main(String[] args) {
//        MedianFinder mf = new MedianFinder();
        MedianFinderQueue mf = new MedianFinderQueue();
        mf.addNum(6);
        System.out.println(mf.findMedian());
        mf.addNum(10);
        System.out.println(mf.findMedian());
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(6);
        System.out.println(mf.findMedian());
        mf.addNum(5);
        System.out.println(mf.findMedian());
        mf.addNum(0);
        System.out.println(mf.findMedian());
        mf.addNum(6);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());
        mf.addNum(1);
        System.out.println(mf.findMedian());
        mf.addNum(0);
        System.out.println(mf.findMedian());
        mf.addNum(0);
        System.out.println(mf.findMedian());
    }
}

/**
 * 错解题意 中位数 需要排序 数组实现时间复杂度较大 扩容后初始值0不好处理
 *
 * 思考后 在插入数组时排序 亦可通过18/21测试用例。  所需时间太长
 * 时间复杂度O(n^2)
 */
class MedianFinder {

    private int[] data; //初始化数组
    private int n;      //元素个数

    /** initialize your data structure here. */
    public MedianFinder() {
        data = new int[10];
        n = 0;
    }

    public void addNum(int num) {
        reSize();
        int size = n;
        if (n == 0){
            data[n] = num;
        } else {
            //有序数组
            while (size >= 1 && data[size - 1] > num){
                data[size] = data[size - 1];
                size --;
            }
            data[size] = num;
        }
        n ++;
    }

    private void reSize(){
        int size = n;
        if (size >= data.length * 0.75){
            int[] temp = new int[data.length * 2];
            while (size > 0){
                temp[size] = data[size];
                size --;
            }
            data = temp;
        }
    }

    public double findMedian() {
        return n % 2 == 0 ? (double) (data[(n / 2) - 1] + data[n / 2]) / 2
                : data[n / 2];
    }
}

/**
 * 通过优先队列实现 (官方解答)
 */
class MedianFinderQueue{

    PriorityQueue<Integer> queMin;
    PriorityQueue<Integer> queMax;

    public MedianFinderQueue() {
        queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
        queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
    }

    public void addNum(int num){
        if (queMin.isEmpty() || num <= queMin.peek()) {
            queMin.offer(num);
            if (queMax.size() + 1 < queMin.size()) {
                queMax.offer(queMin.poll());
            }
        } else {
            queMax.offer(num);
            if (queMax.size() > queMin.size()) {
                queMin.offer(queMax.poll());
            }
        }
    }

    public double findMedian(){
        if (queMin.size() > queMax.size()) {
            return queMin.peek();
        }
        return (queMin.peek() + queMax.peek()) / 2.0;
    }

}

