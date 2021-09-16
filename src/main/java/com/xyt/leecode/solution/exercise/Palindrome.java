package com.xyt.leecode.solution.exercise;

/**
 * @Author: xyt
 * @Description: 回文字符串
 * @Date: 2021/9/15 16:48
 */
public class Palindrome {

    /**
     * 题目：给你一个字符串 s，找到 s 中最长的回文子串。
     *      示例 1：
     *          输入：s = "babad"
     *          输出："bab"
     *          解释："aba" 同样是符合题意的答案。
     *
     *  思路：
     *
     * @link https://leetcode-cn.com/problems/longest-palindromic-substring/
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int front = 0, len = s.length();
        int back = len;



        return null;
    }

    /**
     * 暴力解法 双指针循环，
     *      回文子串 成轴对称
     * @param s
     * @return
     */
    public static String longestPalindromeVio(String s) {
        int len = s.length();
        if (len < 2) return s;

        //如果不转换为数组， 使用charAt charAt()每一次都会判断是否越界。
        char[] chars = s.toCharArray();
        int maxLen = 1, begin = 0;  //回文串长度。begin 开始位置
        //枚举所有的子串长度 char[i ... j]
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validatePalindrome(chars, i, j)){
                    maxLen = j - i + 1; //j - i + 1 用以确定枚举子串右边界长度
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 验证子串是否是回文字符串
     * @param chars
     * @param left
     * @param right
     * @return
     */
    private static boolean validatePalindrome(char[] chars, int left, int right){
        while (left < right){
            if (chars[left] != chars[right]){
                //轴对称，若前后不相等 则不是回文
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }
}


class PalindromeTest{

    public static void main(String[] args) {
        String s = "cccccccccccccccc";
        System.out.println(Palindrome.longestPalindromeVio(s));
    }
}
