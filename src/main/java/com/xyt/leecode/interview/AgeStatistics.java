package com.xyt.leecode.interview;

import java.io.*;

/**
 * @Author: xyt
 * @Description: alibaba 校招面试题  给定一个年龄文件 统计全国人民各年龄段人数
 * @Date: 2021/8/13 12:35
 */
public class AgeStatistics {


    public static Integer ageStat(String fileName) throws Exception {
        String str = "";

        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
        //定义年龄段数组， ages[1]表示 1岁， 值表示人数
        int[] ages = new int[200];
        //总数
        int total = 0;

        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(isr);
        //要一行一行的读取  减少服务器压力
        //时间复杂度  O(n)
        while ((str = br.readLine()) != null){
            int age = Integer.valueOf(str);
            ages[age] ++;
            total ++;
        }
        System.out.println("总共花费时间：" + (System.currentTimeMillis() - start) + "ms");

        return total;
    }
}
