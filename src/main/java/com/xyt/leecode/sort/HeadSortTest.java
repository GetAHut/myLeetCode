package com.xyt.leecode.sort;

import java.util.Random;

/**
 * 堆排序 参照JDK1.8中 优先阻塞队列实现
 *
 * @Author: xyt
 * @Date: 2022/2/8 8:39
 * @version: 1.0.0
 */
public class HeadSortTest {
    private int[] result;

    public HeadSortTest(){
        result = new int[5];
    }

    public int[] getResult() {
        return result;
    }

    /**
     * 小顶堆构建  (区分堆排序)
     * @param k 下标
     * @param v 值
     */
    public void sort(int k, int v){
        while (k > 0){
            // 如果当前节点不是跟节点， 则求出其父节点
            int parent = (k - 1) >>> 1;
            // 拿到父节点的值 比较
            int temp = result[parent];
            if (v >= temp)
                break;
            // 替换值
            // 小顶堆构建 小的上浮
            result[k] = temp;
            k = parent;
        }
        result[k] = v;
    }

    public static void main(String[] args) throws InterruptedException {
        HeadSortTest test = new HeadSortTest();
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int nextInt = random.nextInt(100);
            System.out.println(nextInt);
            test.sort(i, nextInt);
        }
        System.out.println("-------------------");
        for (int i = 0; i < test.result.length; i++) {
            System.out.println(test.result[i]);
        }

    }
}
