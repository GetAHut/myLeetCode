package com.xyt.leecode.structure.diagram;

import java.util.Scanner;

/**
 * @Author: xyt
 * @Description: 深度优先算法
 * @Date: 2021/9/6 14:01
 */
public class Dfs {


    /**
     * 深度优先算法： 不撞南墙不回头
     *  从一个方向一直走， 知道走的没有路了 在回溯；
     *
     *  算法示例： 迷宫问题， 小美在其中迷路了， 你现在已经知道了小美的位置。
     *      则需要你找出救出小美的最快的路径
     *
     */

    int n, m;           //邻接矩阵长宽
    int dx, dy;         //目标位置
    int[][] data;       //邻接矩阵
    boolean mark[][];   //当前位置是否走过标记
    int minDistance = Integer.MAX_VALUE;    //最小距离，以最大值初始化
    //表示每个点 可以向四周 四个方向移动一位
    //避免使用过多if判断 通过遍历此next数组
    int[][] next = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public Dfs(int n, int m, int dx, int dy, int[][] data, boolean mark[][]){
        this.n = n;
        this.m = m;
        this.dx = dx;
        this.dy = dy;
        this.data = data;
        this.mark = mark;
    }


    /**
     *
     * 时间复杂度分析 因为会存在回溯操作，一个位置有四个方向需要去判断
     * 所以 时间复杂度 最坏情况下 O(n^2)
     *
     * 以深度优先算法求最近的距离
     *  递归
     *  1. 递归出口： x == dx , y == dy; 判断distance与minDistance大小
     *  2. 递归公式： 找到下一个坐标
     * @param x
     * @param y
     * @param distance
     * @return
     */
    public void dfs(int x, int y, int distance){

        if (x == dx && y == dy){    //递归出口， 也就是找到了目标位置
            if (distance < minDistance)
                minDistance = distance; //枚举出每一种路径的长度
            return; //调出当前递归
        }

        //遍历寻找下一个坐标
        for (int i = 0; i < 4; i++) {
            int _x = x + next[i][0];
            int _y = y + next[i][1];

            //判断越界
            if (_x < 1 || _x > n || _y < 1 || _y > m) continue;
            //表示当前位置可走，则进行递归
            if (data[_x][_y] == 0 && !mark[_x][_y]){

                mark[_x][_y] = true;    //标记为走过
                dfs(_x, _y, distance + 1);  //递归
                mark[_x][_y] = false;   //回溯
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 4, m = 5;
        int data[][] = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                data[i][j] = scanner.nextInt();
            }
        }
        int dx = scanner.nextInt();
        int dy = scanner.nextInt();
        boolean mark[][] = new boolean[n + 1][m + 1];
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        mark[x][y] = true;  //标记初始位置
        Dfs dfs = new Dfs(n, m, dx, dy,data, mark);
        dfs.dfs(x, y, 0);
        System.out.println(dfs.minDistance);

    }
}

/**
 * 邻接矩阵
0 0 1 0
0 0 0 0
0 0 1 0
0 1 0 0
0 0 0 1
4 3
1 1
 */
