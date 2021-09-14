package com.xyt.leecode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xyt
 * @Description: leetCode 524 通过删除字符匹配到字典里最长的单词 （中等）
 * @Date: 2021/9/14 8:41
 */
public class LeetCode524 {

    /**
     * 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，
     * 找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
     * 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
     * 示例 1：
     *      输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
     *      输出："apple"
     * 示例 2：
     *      输入：s = "abpcplea", dictionary = ["a","b","c"]
     *      输出："a"
     */

    /**
     *
     * @param s
     * @param dictionary
     * @return
     */
    public static String findLongestWord(String s, List<String> dictionary) {

        //先求出在s中的  长度定义为len， 如果后来者比它大则替换
        //如何判断list（i）在s中
        //双指针和贪心算法， 贪心算法  贪心认为s中前 list(i).length 就是匹配成功的 子串
        String ans = "";
        for (String dic : dictionary) {
            //双指针定义， pre表示遍历dic，next遍历s， dic[pre] = s[next] 则双指针都进一位，否则next++
            int pre = 0, next = 0;
            while (next < s.length() && pre < dic.length()){
                if (s.charAt(next) == dic.charAt(pre)){
                    pre ++;
                }
                next ++;
            }
            if (pre == dic.length()){
                if (pre > ans.length() || (pre == ans.length() && dic.compareTo(ans) < 0)){
                    ans = dic;
                }
            }
        }
        return ans;
    }
}

class LeetCode524Test{

    public static void main(String[] args) {
        List<String> dic = new ArrayList<>();
//        dic.add("ale");
//        dic.add("apple");
//        dic.add("monkey");
//        dic.add("plea");
        dic.add("a");
        dic.add("b");
        dic.add("c");
        System.out.println(LeetCode524.findLongestWord("abpcplea", dic));
    }
}
