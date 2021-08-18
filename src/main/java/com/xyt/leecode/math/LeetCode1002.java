package com.xyt.leecode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xyt
 * @Description: leetCode 查找常用字符 （简单）
 * @Date: 2021/8/18 16:52
 */
public class LeetCode1002 {

    /**
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字
     * 符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，
     * 则需要在最终答案中包含该字符 3 次。
     *
     * 你可以按任意顺序返回答案。
     *
     * 示例 1：
     *      输入：["bella","label","roller"]
     *      输出：["e","l","l"]
     * 示例 2：
     *      输入：["cool","lock","cook"]
     *      输出：["c","o"]
     *
     */

    /**
     * 思路一：
     *  统计每一个字符出现的总次数。
     *
     * @param words
     * @return
     */
    public static List<String> commonChars(String[] words) {

        //存放每一个字符串中出现某一字符串的最少次数
        int[] arr = new int[26];
        Arrays.fill(arr, Integer.MAX_VALUE);
        for (int i = 0; i < words.length; i++) {
            //临时存储个数
            int[] temp = new int[26];
            char[] chars = words[i].toCharArray();
            int index = 0;
            while (index < chars.length){
                temp[chars[index] - 'a'] ++;
                index ++;
            }
            for (int j = 0; j < 26; j++) {
                arr[j] = Math.min(arr[j], temp[j]);
            }
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < arr[i]; ++j) {
                res.add(String.valueOf((char)(i + 'a')));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String[] words = {"bella","label","roller"};
        List<String> list = commonChars(words);
    }
}
