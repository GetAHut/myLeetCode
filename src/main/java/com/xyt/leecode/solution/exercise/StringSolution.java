package com.xyt.leecode.solution.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xyt
 * @Description:
 * @Date: 2021/9/16 17:36
 */
public class StringSolution {

    /**
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
     * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     * 请你实现这个将字符串进行指定行数变换的函数：
     * string convert(string s, int numRows);
     * 示例 1：
     *      输入：s = "PAYPALISHIRING", numRows = 3
     *      输出："PAHNAPLSIIGYIR"
     * 示例 2：
     *      输入：s = "PAYPALISHIRING", numRows = 4
     *      输出："PINALSIGYAHRPI"
     *      解释：
     *      PAYPALISHIRING
     *      P     I    N    1     7    13
     *      A   L S  I G    2   6 8  12 14
     *      Y A   H R       3 5   9 11
     *      P     I         4     10
     *
     *  思路：根据示例， 字符z字形走动，半个z字空间   (nomRows - 2) * 2 + 1
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }
        int curRow = 0;
        boolean down = false;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || numRows - 1 == curRow) down = !down;
            //第一行 或者最后一行 转向。
            curRow += down ? 1 : -1;
        }

        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) {
            ans.append(row);
        }
        return ans.toString();
    }
}

class StringTest{
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
//        String s = "ABC";
        System.out.println(StringSolution.convert(s, 4));

    }
}
