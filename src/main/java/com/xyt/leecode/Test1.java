package com.xyt.leecode;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: abby
 * @Date: 2020/6/1 12:53
 */
public class Test1 {
    /**
     * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
     *
     * 示例 1：
     *
     * 输入: s = "leetcode"
     * 输出: false
     * 示例 2：
     *
     * 输入: s = "abc"
     * 输出: true
     */
    public boolean isUnique(String astr) {
        /** 通过set去重*/
        Set set = new HashSet<>();
        for(int i=0; i < astr.length(); i++){
            set.add(astr.charAt(i));
        }
        return set.size() == astr.length();
    }

    /**
     * 不使用现有数据结构， 使用双for循环。
     * @param str 字符串
     * @return boolean
     */
    public boolean isUnique02(String str){
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            for (int j = 0; j < str.length(); j++) {
                if(j != i && c == str.charAt(j)){
                    count++;
                    break;
                }
            }
        }
        return count == 0;
    }


    /**
     * 计算给定二叉树的所有左叶子之和。
     *
     * 示例：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     */
    public int sumOfLeftLeaves(TreeNode root) {

        return 0;
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        System.out.println(test1.isUnique02("abc"));
    }

    /**
     * 二叉树内部类
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
