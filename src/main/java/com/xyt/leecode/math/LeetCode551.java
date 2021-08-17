package com.xyt.leecode.math;

/**
 * @Author: xyt
 * @Description: leetCode 551 学生出勤记录1 （简单）
 * @Date: 2021/8/17 14:38
 */
public class LeetCode551 {

    /**
     * 给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤
     * 情况（缺勤、迟到、到场）。记录中只含下面三种字符：
     *      'A'：Absent，缺勤
     *      'L'：Late，迟到
     *      'P'：Present，到场
     * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
     * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
     * 学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
     * 如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。
     *
     * 示例 1：
     *
     *      输入：s = "PPALLP"
     *      输出：true
     * 解释：学生缺勤次数少于 2 次，且不存在 3 天或以上的连续迟到记录。
     * 示例 2：
     *
     *      输入：s = "PPALLL"
     *      输出：false
     * 解释：学生最后三天连续迟到，所以不满足出勤奖励的条件。
     */

    /**
     * 思路 1 ： 通过 数组存放 字符出现个数， 其中arr[4]存放连续3天出现次数
     * @param s
     * @return
     */
    public static boolean checkRecord(String s) {

        int[] arr = new int[4];

        //两天直接返回true
        if (s.length() < 2) return true;
        if (s.length() == 2
                && s.charAt(0) == s.charAt(1) && s.charAt(0) == 'A')
            return false;

        for (int i = 0; i < s.toCharArray().length; i++) {
            if (s.charAt(i) == 'A') arr[0] ++;
            if (s.charAt(i) == 'L') arr[1] ++;
            if (s.charAt(i) == 'P') arr[2] ++;
            if (i < s.toCharArray().length - 2){
                if ((s.charAt(i) == s.charAt(i + 1))
                        && (s.charAt(i + 1) == s.charAt(i + 2))
                        && s.charAt(i) == 'L'){
                    arr[3] ++;
                }
            }

        }
        return arr[0] < 2 && arr[3] == 0;
    }

    /**
     * 思路二 使用类似于滑动窗口， 判断13相等， 则将进入判断12相等 在等于L
     *  满足则arr[1]++
     * @param s
     * @return
     */
    public static boolean checkRecordByWin(String s){

        //记录A次数，以及3次L出现次数
        int[] arr = new int[2];

        if (s.length() < 2) return true;
        if (s.length() == 2
                && s.charAt(0) ==s.charAt(1)
                && s.charAt(0) == 'A') return false;

        int front = 0, back = front + 2;
        while (back < s.length()){
            if (s.charAt(front) == 'A') arr[0] ++;
            if (s.charAt(front) == s.charAt(back)){
                if (s.charAt(front) == s.charAt(front + 1)
                        && s.charAt(front) == 'L') arr[1]++;
            }
            front ++;
            back ++;
        }

        return arr[0] < 2 && arr[1] == 0;
    }

    public static void main(String[] args) {
        System.out.println(checkRecord("ALLAPPLLL"));
        System.out.println(checkRecordByWin("AA"));
    }
}
