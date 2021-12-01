package com.xyt.leecode.structure.prime;

/**
 * 判断n个数中有多少个素数  素数：只能被 1和本身整除， 除此之外为合数
 * 埃筛法
 *
 * @Author: xyt
 * @Description:
 * @Date: 2021/12/1 11:15
 */
public class PrimeNumber {

    /**
     * 暴力算法
     * @param n
     * @return
     */
    public static int countPrime(int n){
        int count = 0; //计数器
        // 0 1 不需要处理
        for (int i = 2; i < n; i++) {
            count += isPrime(i) ? 1 : 0;
        }
        return count ;
    }

    /**
     * 判断是否是素数 ，
     * i * i <= x  因为在求是否为素数 是在找其公因子， 2 * 3 = 6， 3 * 2 = 6 所有只需要算 根号x次即可，
     *
     * @param x
     * @return
     */
    private static boolean isPrime(int x){
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 埃筛法 因为一个素数*n 一定是合数
     * @param n
     * @return
     */
    public static int eratosthenes(int n){

        // 设定标志位 false表示素数
        boolean[] isPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!isPrime[i]){
                count += 1;
                //
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        return count;
    }
}

class PrimeNumberTest{

    public static void main(String[] args) {
        System.out.println(PrimeNumber.countPrime(100));
        System.out.println(PrimeNumber.eratosthenes(100));
    }
}
