package com.xyt.leecode.math;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xyt
 * @Description: leetCode 面试题01.04 回文排列
 * @Date: 2021/8/11 16:10
 */
public class Plalindrome {

    /**
     * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
     * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
     * 回文串不一定是字典当中的单词。
     *
     *
     * 示例1：
     *      输入："tactcoa"
     *      输出：true（排列有"tacocat"、"atcocta"，等等）
     *
     * @param s
     * @return
     */
    public static boolean canPermutePalindrome(String s) {

        //要是能回文排列 那么只能有一个字符出现次数是奇数
        if (s.length() < 2) return true;

        char[] c = s.toCharArray();
        //直接排序
        Arrays.sort(c);
        //记录奇数次数
        int odd = 0;
        int count = 1;

        for (int i = 1; i < c.length; i++) {
            if (c[i] == c[i - 1]) count ++;
            else {
                //判断存在几个奇数
                if (count % 2 != 0){
                    if ((++odd) > 1) return false;
                }
                //初始化不相同的字符。最后一个字符需要自己再次梳理
                count = 1;
            }
        }

        //判断数组最后一个字符
        if (count % 2 != 0){
            if ((++odd) > 1) return false;
        }

        return true;
    }

    /**
     * 通过分析， 出现奇次的字符串个数只能为一个
     * 那可以通过Set处理
     * @param s
     * @return
     */
    public static boolean canPermutePalindromeSet(String s){

        Set<Character> set = new HashSet<>();

        for (char c : s.toCharArray()) {
            //如果add()返回false表示已经出现过一次， 直接删除两次。
            if (!set.add(c)){
                set.remove(c);
            }
        }

        return set.size() <= 1;
    }

    /**
     * 位运算解决
     *
     * 在128位的字符中，如果是用int类型，需要4位，但如果使用long类型，
     * 只需要两位就行了。一个记录0-63，一个记录64-127。每一位对应一个字符，
     * 如果当前位置是1，表示有字符了，那么加上当前字符就是2个，我们把它变为0,。
     * 如果当前位置没有字符，我们就把当前位置变为1，表示有一个字符。
     * 最后在判断这两个long类型中1的个数，如果大于1个就不能构成回文排列。
     *
     * @param s
     * @return
     */
    public static boolean canPermutePalindromeBit(String s){
        long highBitMap = 0;
        long lowBitMap = 0;

        for (char c : s.toCharArray()) {
            //char的加减运算
            //char的加减运算就是按其Unicode编号进行运算
            //大写A～Z的编号是65～90，小写a～z的编号是97～122，正好相差32，所以大写转小写只需加32，而小写转大写只需减32。
            //加减运算的另一个应用是加密和解密，将字符进行某种可逆的数学运算可以做加解密。
            //char的位运算可以看作是对应整数的位运算，只是它是无符号数，也就是说，有符号右移>>和无符号右移>>>的结果是一样的
            System.out.println(c - 64);
            if (c >= 64){
                //^=  左边操作数与右边操作数按位异或后，赋值与左边操作数
                highBitMap ^= 1L << c - 64;
            } else {
                lowBitMap ^= 1L << c;
            }
        }

        return Long.bitCount(highBitMap) + Long.bitCount(lowBitMap) <= 1;
    }


    public static void main(String[] args) {

        System.out.println(canPermutePalindromeBit("abs"));
    }
}
