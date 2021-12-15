package com.xyt.leecode.solution.exercise;

/**
 * @Author: xyt
 * @Date: 2021/12/15 8:36
 * @version: 1.0.0
 */
public class Sqrt {

    // 求X的平方根 返回整数部分
    /**
     * 暴力算法 当 i * i < x && (i + 1) * (i + 1) > x
     */
    public static int violence(int x){
        int res = -1;
        // 循环 可以从
        for (int i = 0; i < x; i++) {
            if ((i * i <= x) && ((i + 1) * (i + 1)) > x) {
                res = i;
            }
        }
        return res;
    }

    /**
     * 二分查找法
     * @param x
     * @return
     */
    public static int binarySearch(int x){
        int res = -1, l = 0, r = x;

        while (l <= r){
            // 取中位数
            // ---|----|---|-------|--------->
            //    0    l  mid      r  ====> l + (r - l) / 2
            // mid 其实为0 - r的中位数
            int mid = l + (r - l) / 2;
            if (mid * mid <= x){
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }

    /**
     * 牛顿迭代的方式 -> 例如  12  为 1 * 12 , 2 * 6 ,3 * 4  两个因子的均值 是越来越接近 平方根
     *  所有有公式  n^2 = x ====> x / n  = n  依旧是说 x / n 和  n的均值 最接近 x平凡跟 即可
     * @param x
     * @return
     */
    public static int newTon(int x){
        if (x == 0){
            return x;
        }
        return (int)recursion(x, x);
    }

    /**
     * 递归求均值
     * @param i
     * @param x
     * @return
     */
    private static double recursion(double i, int x){
        double res = (x / i + i) / 2;

        if (res == i){
            return i;
        } else {
            return recursion(res, x);
        }
    }

}

class SqrtTest{

    public static void main(String[] args) {
        System.out.println(Sqrt.violence(12));
        System.out.println(Sqrt.binarySearch(36));
        System.out.println(Sqrt.newTon(12));
    }
}
