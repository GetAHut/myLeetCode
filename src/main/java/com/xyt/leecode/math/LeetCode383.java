package com.xyt.leecode.math;

/**
 * @Author: xyt
 * @Description: leetCode 383 赎金信 （简单）
 * @Date: 2021/8/16 16:53
 */
public class LeetCode383 {

    /**
     * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，
     * 判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。
     * 如果可以构成，返回 true ；否则返回 false。
     * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，
     * 组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
     *
     * 示例 1：
     *      输入：ransomNote = "a", magazine = "b"
     *      输出：false
     * 示例 2：
     *      输入：ransomNote = "aa", magazine = "ab"
     *      输出：false
     * 示例 3：
     *      输入：ransomNote = "aa", magazine = "aab"
     *      输出：true
     */

    /**
     *
     * @param ransomNote 赎金信
     * @param magazine  杂志
     * @return
     */
    public static boolean canConstruct(String ransomNote, String magazine) {

        //思路： 假设只有小写字母
        // 通过数组计数的方式实现

        if (ransomNote.length() > magazine.length()) return false;

        //杂志中字母出现个数
        int[] arr_m = new int[26];
        //赎金信中字母个数
        int[] arr_r = new int[26];

        for (char m : magazine.toCharArray()) { //O(n)
            arr_m[m - 'a'] ++;
        }

        for (char r : ransomNote.toCharArray()) { //O(n)
            arr_r[r - 'a'] ++;
        }

        int index = 0;
        while (index < arr_r.length){   //O(n)
            //当赎金信中个数大于 杂志中 直接返回
            if (arr_r[index] != 0 && arr_r[index] > arr_m[index]){
                return false;
            }
            index ++;
        }

        return true;
    }

    public static void main(String[] args) {
        //"fihjjjjei"
        //"hjibagacbhadfaefdjaeaebgi"
        System.out.println(canConstruct("fihjjjjei", "hjibagacbhadfaefdjaeaebgi"));
    }
}
