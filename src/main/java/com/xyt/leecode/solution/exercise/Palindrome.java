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
     *  思路：使用动态规划
     *      状态转移方程： dp[i][j] = dp[i + 1][j - 1];
     *          其中dp[i][j] 表示s[i] == s[j]
     *
     *   关键点： 求出状态转移方程
     *          根据左边界以及子串长度 求出右边界。
     *
     * @link https://leetcode-cn.com/problems/longest-palindromic-substring/
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;

        int maxLen = 1, begin = 0;  //定义最大子串长度，以及起始位置。
        //标记表示dp[i][j] ，表示s[i ... j]是否是回文串
        boolean dp[][] = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;    //初始化
        }

        char[] chars = s.toCharArray();
        //枚举子串长度
        for (int L = 2; L <= len; L++) {
            //枚举左边界位置
            for (int i = 0; i < len; i++) {
                //可以根据L i 确定有边界位置。j - i + 1 = L：
                int j = L + i - 1;  //右边界
                if (j >= len) break; //右边界越界直接跳出当前循环

                if (chars[i] != chars[j]){
                    dp[i][j] = false;
                } else {
                    if (j - i < 3){ //j - i < 3表示子串长度不超过3,两端相等则就是回文串
                        dp[i][j] = true;
                    } else {
                        //状态转移方程
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                //只要是dp[i][j]= true 则表示s[i...j]是回文子串。
                if (dp[i][j] && j - i + 1 > maxLen){
                    maxLen = j - i + 1; //最大长度更新
                    begin = i;  //起始位置
                }
            }
        }
        return s.substring(begin, begin + maxLen);
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
