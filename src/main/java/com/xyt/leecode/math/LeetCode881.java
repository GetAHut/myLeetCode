package com.xyt.leecode.math;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description:  leetCode 881 救生艇(中等)
 * @Date: 2021/8/26 8:56
 */
public class LeetCode881 {

    /**
     * 第i个人的体重为people[i]，每艘船可以承载的最大重量为limit。
     * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为limit。
     * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
     * 示例 1：
     *      输入：people = [1,2], limit = 3
     *      输出：1
     *      解释：1 艘船载 (1, 2)
     * 示例 2：
     *      输入：people = [3,2,2,1], limit = 3
     *      输出：3
     *      解释：3 艘船分别载 (1, 2), (2) 和 (3)
     * 示例 3：
     *      输入：people = [3,5,3,4], limit = 5
     *      输出：4
     *      解释：4 艘船分别载 (3), (3), (4), (5)
     */

    /**
     *  思路：排序后 前后遍历， 每艘船最多载两人
     *      贪心算法 将每一艘船载最多的人
     * @param people
     * @param limit
     * @return
     */
    public static int numRescueBoats(int[] people, int limit) {

        //计数器  前后指针
        int ans = 0, front = 0, back = people.length - 1;
        Arrays.sort(people);    //排序
        while (front <= back){
            if (people[back] + people[front] <= limit)
                front ++;
            ans ++;
            back --;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] people = {3,2,2,1};
        System.out.println(numRescueBoats(people, 3));
    }
}
