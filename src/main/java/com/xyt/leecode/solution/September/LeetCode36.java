package com.xyt.leecode.solution.September;

/**
 * @Author: xyt
 * @Description: leetCode 36 有效的数独(中等)
 * @Date: 2021/9/17 8:45
 */
public class LeetCode36 {

    /**
     * 请你判断一个9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     *
     * 数字1-9在每一行只能出现一次。
     * 数字1-9在每一列只能出现一次。
     * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
     * 数独部分空格内已填入了数字，空白格用'.'表示。
     */

    /**
     * 思路： 判断重复的方式有  set、 hash、 数组计数方式、bitmap。。。
     *      所以此处 可以使用hash 或者数组，因为数据 9 * 9 相对较少 下标 均在0-9之间。
     *
     *      三种情况 。两个二位数组判断，第n行 第m列 是否重复。
     *              三维数组判断 小九宫格是否重复。
     *
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {

        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] sub = new int[3][3][9];
        for (int n = 0; n < 9; n++) {
            for (int m = 0; m < 9; m++) {
                char c = board[n][m];
                if (c != '.'){
                    int index = c - '0' - 1;
                    rows[n][index] ++;
                    columns[m][index] ++;
                    sub[n / 3][m /  3][index] ++;
                    if (rows[n][index] > 1 || columns[m][index] > 1 || sub[n / 3][m / 3][index] > 1){
                        return false;
                    }
                }

            }
        }
        return true;
    }
}
