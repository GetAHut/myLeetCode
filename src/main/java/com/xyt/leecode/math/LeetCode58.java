package com.xyt.leecode.math;

/**
 * @Author: xyt
 * @Description: leetCode 58 最后一个单词的长度 （简单）
 * @Date: 2021/8/15 12:28
 */
public class LeetCode58 {

    /**
     *
     给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
     单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
     示例 1：
         输入：s = "Hello World"
         输出：5
     示例 2：
         输入：s = "   fly me   to   the moon  "
         输出：4
     示例 3：
         输入：s = "luffy is still joyboy"
         输出：6
     */

    /**
     * 套用spring api
     * @param s
     * @return
     */
    public static int lengthOfLastWordByStringApi(String s){

        if (s.trim().length() == 1){
            return 1;
        }
        String[] s1 = s.trim().split(" ");
        return s1[s1.length -1 ].length();
    }

    /**
     * 双指针遍历移动，类似滑动窗口算法
     *  ‘ ’ = 32
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s){

        int count = 0;
        char[] chars = s.toCharArray();

        //前后指针
        int back = s.length() -1, front = back;
        boolean isBlank = true;

        while (isBlank && front >= 0){
            //后指针移动
            if (chars[back] != 32){
                //开始走前指针
                if (chars[front] != 32){
                    front --;
                } else {
                    isBlank = false;
                }
            } else {
                back --;
                front = back;
            }
            count = back - front;
        }

        return count;
    }

    public static int lengthOfLastWord1(String s) {
        int length = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                length++;
            } else if (length != 0) {
                return length;
            }
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWordByStringApi("   fly me   to   the moon  "));
        System.out.println(lengthOfLastWord("World"));
        System.out.println(lengthOfLastWord1("   fly me   to   the moon  "));
    }
}
