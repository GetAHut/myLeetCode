package com.xyt.leecode.interview;

/**
 * @Author: xyt
 * @Description: 斐波那锲数列
 * @Date: 2021/8/19 12:22
 */
public class Fibonacci {

    private static int cache[];

    /**
     * 求斐波那锲数列
     *  示例： 1,1,2,3,5,8,13.....
     *
     *      递归思路：
     *          1. 递归出口（必须有一个确定的答案）： 当n <= 2时 数列为1
     *          2. 找出子问题，并找到运算公式
     *          3. 递归公式： f(n) = f(n-1) + f(n-2)
     * @param n
     * @return
     */
    public static int getFibonacci(int n){//树状结构，时间复杂度和空间复杂度都是为O(2^n)
        if (n <= 2) return 1;
        return getFibonacci(n-1) + getFibonacci(n - 2);
    }

    /**
     * 不使用递归。
     *  任何递归都可以还原成非递归
     * @param n
     * @return
     */
    public static int getFibonacciWithoutRecursion(int n){  //时间复杂度为O(n)
        if (n <= 2)
            return 1;
        int a = 1, b = 1, c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            //循环赋值
            a = b;
            b = c;
        }
        return c;
    }

    /**
     *  通过缓存还优化递归
     *  时间空间复杂度优化为O(n)
     * @param n
     * @return
     */
    public static int getFibonacciByRecursionWithCache(int n){
        if (n <= 2) return 1;   //递归出口
        if (cache[n] > 0) return cache[n];  //如果存在则直接返回
        //存入缓存
        int ans = getFibonacciByRecursionWithCache(n - 1)
                + getFibonacciByRecursionWithCache(n - 2);
        cache[n] = ans;
        return ans;
    }

    /**
     * 通过尾递归优化： 时间空间复杂度O(n)
     *  尾递归： 即 调用函数一定出现在末尾，没有任何其他的操作了。
     *      如： return f(n - 1) + f(n - 2) 在里存在运算 则不是尾递归。
     *          return f(n - 1)满足条件
     *    如果还存在运算，则会将运算结果存入栈中，消耗时间、空间；如果编译器发现末尾
     *   没有操作了，则不会创建新的栈，
     *
     *
     * @param n 当前位置
     * @param pre 上一步运算的结果
     * @param res 当前运算的结果
     * @return
     */
    public static int getFibonacciByTailRecursion(int n, int pre, int res){
        //这里不能再返回1 ， 因为 尾递归不会回溯； n=1 直接终止。
        if (n <= 2) return res;
        /**
         * n - 1： 向前移动一位
         * res： 上一步的当前运算结果 这里就是上一步运算的结果
         * pre + res ：上一步运算结果加上上步运算结果
         */
        return getFibonacciByTailRecursion(n - 1, res, pre + res);
    }

    /**
     * 阶乘尾递归示例
     * @param n 当前位置
     * @param res 上一次运算结果
     * @return
     */
    public static int factorialTailRecursion(int n, int res){

        if (n <= 1) return res;

        //n - 1 表示 下一步递归， n * res 表示用作下一步递归的上一次运算结果
        return factorialTailRecursion(n - 1, n * res);
    }

    /**
     * 阶乘  正常递归展示
     *      1. 递归出口： 0和1的阶乘都是1--> n <= 1 return 1;
     *      2. 子问题： f(n) = n * f(n-1)   1*2*3*4*5....
     *      3. 公式 ：f(n) = n * f(n-1)
     * @param n
     * @return
     */
    public static int factorialRecursion(int n){
        if (n <= 1) return 1;
        return n * factorialRecursion(n - 1);
    }


    public static void main(String[] args) {

        for (int i = 1; i <= 10; i++) {
            System.out.println(getFibonacci(i));
        }

        //初始化缓存cache
        cache = new int[46];
        for (int i = 1; i <= 45; i++) {
            long start = System.currentTimeMillis();
            System.out.println(i + ": " + getFibonacciByRecursionWithCache(i)
                    + "  所花费时间为：" + (System.currentTimeMillis() - start) + "ms");
        }

        System.out.println("--------------------------------------");

        //尾递归
        for (int i = 1; i <= 45; i++) {
            long start = System.currentTimeMillis();
            System.out.println(i + ": " + getFibonacciByTailRecursion(i, 1, 1)
                    + "  所花费时间为：" + (System.currentTimeMillis() - start) + "ms");
        }

        for (int i = 1; i <= 5; i++) {
            System.out.println(factorialRecursion(i));
        }
    }
}
