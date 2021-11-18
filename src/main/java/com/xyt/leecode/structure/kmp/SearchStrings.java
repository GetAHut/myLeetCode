package com.xyt.leecode.structure.kmp;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description: 字符串搜索 kmp算法
 * @Date: 2021/11/18 18:16
 */
public class SearchStrings {

    /**
     *  topic： 给定两个字符串AB， 判断B在A中是否存在， 存在则返回A中的下标， 不存在则返回-1；
     *  eg： A： ABCABCAABCABCD
     *      B: ABCABCD
     *
     *  answer: 很明显是 7
     *
     *
     */

    /**
     * kmp算法
     * @param str A
     * @param chars B
     * @param next pmt值又移一位后数组
     * @return
     */
    public static int search(char[] str, char[] chars, int[] next){
        int i = 0, j = 0;
        while (i < str.length && j < chars.length){
            if (j == -1 || chars[j] == str[i]){
                i ++;
                j ++;
            } else {
                // 移动模式串
                j = next[j];
            }
        }

        if (j == chars.length){
            return i - j;
        } else {
            return -1;
        }
    }

    /**
     *
     * @param chars 模式串
     * @param next 数组
     * @return
     */
    public static void getNext(char[] chars,  int[] next){
        next[0] = -1;
        int i = 0, j = -1;
        while (i < chars.length){
            if (j == -1){
                i ++;
                j ++;
            } else if (chars[i] == chars[j]){
                i ++;
                j ++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
    }
}

class KmpTest{
    public static void main(String[] args) {
        String str = "ABCABCAABCABCD";
        String chars = "ABCABCD";
        int[] next = new int[chars.length()];
        SearchStrings.getNext(chars.toCharArray(), next);
        int search = SearchStrings.search(str.toCharArray(), chars.toCharArray(), next);
        System.out.println(Arrays.toString(next));
        System.out.println(search);
    }
}
