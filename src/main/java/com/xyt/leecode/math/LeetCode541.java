package com.xyt.leecode.math;

/**
 * @Author: xyt
 * @Description: leetCode 541 反转字符串 II (简单)
 * @Date: 2021/8/20 16:45
 */
public class LeetCode541 {

    /**
     * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
     * 如果剩余字符少于 k 个，则将剩余字符全部反转。
     * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
     *
     * 示例 1：
     *      输入：s = "abcdefg", k = 2
     *      输出："bacdfeg"
     * 示例 2：
     *      输入：s = "abcd", k = 2
     *      输出："bacd"
     */

    /**
     * 思路：滑动窗口
     * @param s
     * @param k
     * @return
     */
    public static String reverseStr(String s, int k) {

        int front = 0, back = front + 2 * k;
        char[] chars = s.toCharArray();

        while (back <= s.length()){
            //2k大小窗口 反转
            int _back = back - k - 1;
            while (front < _back){
                char temp = chars[front];
                chars[front] = chars[_back];
                chars[_back] = temp;
                front ++;
                _back --;
            }
            front = back;
            back += 2 * k;
        }

        int _back = s.length() - 1;
        back = back - 2 * k;
        if (s.length() - back > 0) {
            if (s.length() - back >= k){
                _back = back + k - 1;
            }
            while (back < _back){
                char temp = chars[back];
                chars[back] = chars[_back];
                chars[_back] = temp;
                back ++;
                _back --;
            }
        }
        return String.valueOf(chars);
    }

    /**
     * 官方解答： 我们直接按题意进行模拟：反转每个下标从 2k 的倍数开始的，
     *      长度为 k 的子串。若该子串长度不足 k，则反转整个子串。
     * @param s
     * @param k
     * @return
     */
    public static String reverseStrOfficial(String s, int k){
        int n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            // i, 窗口左边界， 取 i+k和 s长度最小值 - 1作为有边界；
            reserve(chars, i, Math.min(i + k, n) - 1);
        }
        return new String(chars);
    }

    private static void reserve(char[] arr, int left, int right){
        while (left < right){
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left ++;
            right --;
        }
    }

    public static void main(String[] args) {

        String s = "krmyfshbspcgtesxnnljhfursyissjnsocgdhgfxubewllxzqhpasguvlrxtkgatzfybprfmmfithphckksnvjkcvnsqgsgosfxc";
        System.out.println(reverseStr(s, 20));
    }
}
