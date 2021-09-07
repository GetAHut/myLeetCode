package com.xyt.leecode.structure.diagram;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author: xyt
 * @Description: 广度优先算法
 * @Date: 2021/9/6 14:02
 */
public class Bfs {

    /**
     * 针对图数据结构
     *
     *      点少就是用邻接矩阵， 点多就用邻接表
     *
     *  当 当前节点A 可以达到 b 、c 等节点， b 可以到c 、d 、f等
     *
     *  广度优先算法： 先将自己存入队列， 然后想自己能到达的节点放入队列（若节点已经存在则不需要放入）
     *              将自己出队，在对队列第一个 进行同样操作。
     *
     *     算法示例： 迷宫，当小美在迷宫中迷路了 你需要去找到他。
     */

    int n, m;           //矩阵长宽 //二维数组长宽
    int dx, dy;         //目标位置
    int[][] data;       //邻接矩阵
    boolean mark[][];   //走过的位置标记状态
    //表示每个点 可以向四周 四个方向移动一位
    //避免使用过多if判断 通过遍历此next数组
    int[][] next = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public Bfs(int n, int m, int dx, int dy, int[][] data, boolean[][] mark){
        this.n = n;
        this.m = m;
        this.dx = dx;
        this.dy = dy;
        this.data = data;
        this.mark = mark;
    }

    /**
     *
     * 时间复杂度分析： O(n)
     *
     *  x 和 y 表示你当前的位置， 问题就转换成了(x, y) -> (dx, dy)能不能达到
     * @param x
     * @param y
     * @return
     */
    public int dfs(int x, int y){
        mark[x][y] = true;  //当前位置走过标记为true

        //初始化队列。 队列大小 即为 矩阵点个数
        Queue<Point> queue = new ArrayBlockingQueue<>(n * m);
        //将点入队列
        Point now = new Point(x, y);
        queue.add(now);

        //队列为空为止
        while (!queue.isEmpty()){
            Point poll = queue.poll();  //拿出队列的第一个点
            for (int i = 0; i < 4; i++) {
                int _x = poll.x + next[i][0];
                int _y = poll.y + next[i][1];
                //判断是否越界
                if (_x < 1 || _x > n || _y < 1 || _y > m) continue;
                //判断是否next是否可以走 且是否已经走过
                //0表示无障碍物可走，mark=true表示已经走过的。
                if (data[_x][_y] == 0 && !mark[_x][_y]){
                    if (_x == dx && _y == dy){
                        return 0;   //表示走到了 则不需要在动了
                    }
                    //下一步节点 入队
                    Point point = new Point(_x, _y);
                    queue.add(point);
                    mark[_x][_y] = true;
                }
            }
        }
        //没找到 返回-1
        return -1;
    }
}

//点坐标位置
class Point{
    int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class BfsTest{
    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);
        int n = 4, m = 5;
        int[][] data = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                data[i][j] = sin.nextInt();
            }
        }

        int dx = sin.nextInt();
        int dy = sin.nextInt();

        boolean[][] mark = new boolean[n + 1][m + 1];
        int x = sin.nextInt();
        int y = sin.nextInt();
        mark[x][y] = true;  //当前位置

        Bfs bfs = new Bfs(n, m, dx, dy, data, mark);
        System.out.println(bfs.dfs(x, y));
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
