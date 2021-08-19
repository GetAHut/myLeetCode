package com.xyt.leecode.math;

/**
 * @Author: xyt
 * @Description: leetCode 345 反转字符串中的元音字母(简单)
 * @Date: 2021/8/19 16:59
 */
public class LeetCode345 {

    /**
     * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     * 元音字母有 a、e、i、o、u
     * 示例 1：
     *
     * 输入："hello"
     * 输出："holle"
     * 示例 2：
     *
     * 输入："leetcode"
     * 输出："leotcede"
     */

    public static String reverseVowels(String s) {

        //双指针遍历
        char[] chars = s.toCharArray();
        int front = 0, back = chars.length - 1;
        while (front < back){
            while (front < chars.length && !isVowels(chars[front])){
                ++front;
            }
            while (back > 0 && !isVowels(chars[back])){
                --back;
            }
            if (front < back){
                char temp = chars[front];
                chars[front] = chars[back];
                chars[back] = temp;
                //移动指针 遍历下一个字符
                ++front;
                --back;
            }
        }

        return String.valueOf(chars);
    }

    private static boolean isVowels(char c){
        return "aeiouAEIOU".indexOf(c) >= 0;
    }

    public static void main(String[] args) {
        System.out.println(reverseVowels("race car"));
    }
}
