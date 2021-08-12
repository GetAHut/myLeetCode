package com.xyt.leecode.math;

import java.util.*;

/**
 * @Author: xyt
 * @Description: leetCode 438 （中等） 找到字符串中的所有字母异位词
 * @Date: 2021/8/12 15:42
 */
public class Anagrams {

    /**
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，
     * 返回这些子串的起始索引。不考虑答案输出的顺序。
     * 异位词 指字母相同，但排列不同的字符串。
     *
     * 示例 1:
     *
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     *      起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     *      起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     *
     *  示例 2:
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     *      起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     *      起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     *      起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p){
        //解题思路：
        //      滑动指针框架
        int start = 0, left = 0, right = 0, match = 0;
        List<Integer> ans = new ArrayList<>();

        //滑动窗口
        Map<Character, Integer> windows = new HashMap<>();
        //p
        Map<Character, Integer> needs = new HashMap<>();

        //对p处理
        for (char c : p.toCharArray()) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }

        //窗口右指针 不能超出s
        while (right < s.length()){

            char _c = s.charAt(right);
            if (needs.containsKey(_c)){
                windows.put(_c, windows.getOrDefault(_c, 0) + 1);
                if (windows.get(_c).equals(needs.get(_c)))
                    match ++;
            } else {
                left = right + 1;
                //重置窗口
                windows = new HashMap<>();
                match = 0;
            }
            right ++;

            //左指针移动
            while (match == needs.size()){

                start = left;
                if (windows.equals(needs)) ans.add(start);

                char c2 = s.charAt(left);
                if (windows.containsKey(c2)){
                    windows.put(c2, windows.get(c2) -1);
                    if (windows.get(c2) < needs.get(c2))
                        match --;
                }
                left ++;
            }
        }

        return ans;
    }

    /**
     * 通过滑动窗口 + 数组
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagramsArr(String s, String p){

        List<Integer> ans = new ArrayList<>();
        int s_len = s.length(), p_len = p.length();

        //都是小写字母  直接26个数组定义
        int[] s_arr = new int[26];
        int[] p_arr = new int[26];

        if (s_len < p_len) return ans;

        //滑动窗口
        for (int i = 0; i < p_len; i++) {
            s_arr[s.charAt(i) - 'a'] ++;
            p_arr[p.charAt(i) - 'a'] ++;
        }

        for (int i = -1; i < s_len - p_len; i++) {
            if (i >= 0){
                //滑动指针 固定窗口
                s_arr[s.charAt(i) - 'a'] --;
                s_arr[s.charAt(i + p_len) - 'a'] ++;
            }

            boolean isAnagrams = true;
            for (int j = 0; j < 26; j++) {
                //&= 按位与后赋值
                isAnagrams &= s_arr[j] == p_arr[j];
                if (!isAnagrams) break;
            }
            if (isAnagrams) ans.add(i + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagramsArr("cbaebabacd", "abc"));
    }
}
