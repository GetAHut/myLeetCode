package com.xyt.leecode;

import java.io.UnsupportedEncodingException;
import java.util.*;

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
    int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        getSum(root, false);
        return sum;
    }

    private void getSum(TreeNode root, boolean ifLeft){
        if (root != null){
            if (ifLeft && root.left == null && root.right == null){
                sum += root.val;
            } else {
                //递归
                //相当于遍历下一个节点，并将这个节点作为根节点。
                getSum(root.left, true);
                getSum(root.right, false);
            }
        }
    }

    /**
     * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
     *
     * 注意:
     *
     * 十六进制中所有字母(a-f)都必须是小写。
     * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
     * 给定的数确保在32位有符号整数范围内。
     * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
     * 示例 1：
     *
     * 输入:
     * 26
     *
     * 输出:
     * "1a"
     */

    public String toHex(int num){
        StringBuffer s = new StringBuffer();
        //先不考虑负数 补码运算
        char[] hex = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        if (num == 0){
            return "0";
        }
        while (num != 0){
            //& 位运算 与 于此类似还有 或与非
            s = s.append(hex[num&15]);
            //无符号右移4位
            num >>>= 4;
        }
        return s.reverse().toString();
    }

    /**
     * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
     *
     * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
     *
     * 注意:
     * 假设字符串的长度不会超过 1010。
     *
     * 示例 1:
     *
     * 输入:
     * "abccccdd"
     *
     * 输出:
     * 7
     *
     * 解释:
     * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     */
    public int longestPalindrome(String s) {
        int sum = 0;
        boolean flag = true;
        int count = 0;
        List<Integer> list = new ArrayList<>();
//        List<Integer> list2 = new ArrayList<>();
        Set set = new HashSet();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        for (int i = 0; i < set.toArray().length; i++) {
            for (int j = 0; j < s.length(); j++) {
                if ((char)set.toArray()[i] == s.charAt(j)){
                    count++;
                }
            }
            list.add(count);
            count = 0;
        }
        //将所有字母出现次数放到list中后， 取所有偶数之和，
        //以及加上除1以为所有奇数之和
        //在减去所加奇数个数 再+1
        //遍历list
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0){
                sum += list.get(i);
            }else {
                //将奇数单独存入list
//                list2.add(list.get(i));
                //若奇数 则为 sum + 奇数 -1
                sum += list.get(i) - 1;
                flag = false;
            }
        }
        if (flag){
            return sum;
        }
        return sum + 1;

//        if (list2.size() == 0){
//            return sum;
//        }
//        for (int i = 0; i < list2.size(); i++) {
//            if (list2.get(i) != 1){
//                sum += list2.get(i) - 1;
//            }
//        }
//
//        return sum + 1;
    }

    public int longestPalindrome01(String s){
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        int ans = 0;
        for (int v : count) {
            ans = v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0){
                ans++;
            }
        }
        return ans;

    }



    public static void main(String[] args) {
        Test1 test1 = new Test1();
        System.out.println(test1.isUnique02("abc"));
        System.out.println(test1.toHex(78));
        System.out.println(test1.longestPalindrome("abccccdd"));
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
