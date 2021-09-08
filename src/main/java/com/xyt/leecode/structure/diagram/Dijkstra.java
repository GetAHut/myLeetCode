package com.xyt.leecode.structure.diagram;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: xyt
 * @Description: 迪杰斯特拉算法 求最短路劲
 * @Date: 2021/9/7 15:37
 */
public class Dijkstra implements Cloneable {

    /**
     * 用到贪心算法、动态规划思想。
     * 笔记 @link https://app.yinxiang.com/fx/13d7a2dc-31fd-40ee-a3f3-b626df681d1b
     */

    //最短路径
    static Map<String, String> path = new HashMap<>();

    /**
     *
     * @param x 起点
     * @param dis   到每个点的最小距离数据
     * @param weights  两点之间既定的距离，（权重）
     * @param n 总共有多少个点
     * @return
     */
    public static void search(int x, int[] dis, int[][] weights, int n){

        //标记数组 ， 表示计算过的点不在计算
        boolean[] mark = new boolean[n + 1];
        mark[x] = true; //当前点标记
        dis[x] = 0;
        int count = 1;
        while (count <= n){
            int loc = 0; //表示新加的点
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                //求dis[]中最小值 !mark[i] 表示已经找过的点
                if (!mark[i] && dis[i] < min){
                    min = dis[i];
                    loc = i;
                }
            }
            if (loc == 0) break; //表示没有可以在加的点了
            mark[loc] = true; //    将加入的点位置标记为已处理

            //判断点是否已经加入map
            if (!path.containsKey(loc + "")){
                path.put(loc + "", x + "->" + loc);
            }

            //松弛处理 （动态规划的状态转移方程）
            //dis[3] + weights[3][i] < dis[i]
            for (int i = 1; i <= n; i++) {
                //状态方程程序化，weights[loc][i] != -1  其中-1表示两点之间没有路
                if (weights[loc][i] != -1 &&
                        (dis[loc] + weights[loc][i] < dis[i])){
                    //赋值 求最短距离
                    dis[i] = dis[loc] + weights[loc][i];
                    //求最短路径
                    path.put(i + "", path.get(loc + "") + "->" + i);
                }
            }
            count++;
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(x + "到" + i + "的距离为：" + dis[i]);
            if (path.get(i + "") == null){
                path.put(i + "", x + "无法到达" + i);
            }
            System.out.println(x + "到" + i + "的最短路径为" + path.get(i + ""));
        }
    }
}

class DijkstraTest{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m, x;    //n 表示多少个点， m表示多少条边， x表示当前位置
        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();

        //初始化权重数组
        int[][] weights = new int[n + 1][n + 1];
        int[] dis = new int[n + 1]; //最小距离数组
        for (int i = 1; i <= n; i++) {
            dis[i] = Integer.MAX_VALUE; //距离数组初始化最大值
            for (int j = 1; j <= n; j++) {
                //初始化边权重， 默认-1 表示均不可达到
                weights[i][j] = -1;
                if (i == j){
                    weights[i][j] = 0;  //自己所在位置到达自己距离为0
                }
            }
        }

        for (int i = 0; i < m; i++) {
            int dx = sc.nextInt();  //起点
            int dy = sc.nextInt();  //到某一个点 比如起点1 到2的点
            int weight = sc.nextInt();  // 从起点到某一点的距离权重
            weights[dx][dy] = weight;
            if (dx == x){   //第一个点计算
                //计算第一个点的权重， 1可以达到3/5/6分别距离为10/30/100
                //则dis[3] = 10 dis[5]=30 dis[6]==100 dis数组从1开始计算
                dis[dy] = weight;
            }
        }

        Dijkstra.search(x, dis, weights, n);
    }
}

/**
6
8
1
1 3 10
1 5 30
1 6 100
2 3 5
3 4 50
4 6 10
5 4 20
5 6 60
 */