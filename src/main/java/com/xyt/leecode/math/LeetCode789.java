package com.xyt.leecode.math;

/**
 * @Author: xyt
 * @Description: leetCode 789 逃脱阻碍者（中等）
 * @Date: 2021/8/22 9:37
 */
public class LeetCode789 {

    /**
     * 你在进行一个简化版的吃豆人游戏。你从 [0, 0] 点开始出发，
     * 你的目的地是target = [xtarget, ytarget] 。
     * 地图上有一些阻碍者，以数组 ghosts 给出，
     * 第 i 个阻碍者从ghosts[i] = [xi, yi]出发。所有输入均为 整数坐标 。
     * 每一回合，你和阻碍者们可以同时向东，西，南，北四个方向移动，
     * 每次可以移动到距离原位置 1 个单位 的新位置。当然，也可以选择 不动 。所有动作 同时 发生。
     * 如果你可以在任何阻碍者抓住你 之前 到达目的地（阻碍者可以采取任意行动方式），
     * 则被视为逃脱成功。如果你和阻碍者同时到达了一个位置（包括目的地）都不算是逃脱成功。
     * 只有在你有可能成功逃脱时，输出 true ；否则，输出 false 。
     *
     * 示例 1：
     *      输入：ghosts = [[1,0],[0,3]], target = [0,1]
     *      输出：true
     *      解释：你可以直接一步到达目的地 (0,1) ，在 (1, 0) 或者 (0, 3) 位置的阻碍者都不可能抓住你。
     * 示例 2：
     *      输入：ghosts = [[1,0]], target = [2,0]
     *      输出：false
     *      解释：你需要走到位于 (2, 0) 的目的地，但是在 (1, 0) 的阻碍者位于你和目的地之间。
     * 示例 3：
     *      输入：ghosts = [[2,0]], target = [1,0]
     *      输出：false
     *      解释：阻碍者可以和你同时达到目的地。
     * 示例 4：
     *      输入：ghosts = [[5,0],[-10,-2],[0,-5],[-2,-2],[-7,1]], target = [7,7]
     *      输出：false
     * 示例 5：
     *      输入：ghosts = [[-1,0],[0,1],[-1,0],[0,1],[-1,0]], target = [0,0]
     *      输出：true
     */

    /**
     * 思路：在阻碍着抓到之前 达到目标地点则视为逃脱成功。
     *      每一次移动只能移动一位， 逃脱者从00开始移动
     *       target[0] + target[1] 为总共需要走的步数， 阻碍着在这步数之内到达目标点就失败
     *
     *    ghosts_step :曼哈顿距离 参考坐标系两点距离（非直线距离）
     *    所以 阻碍着与目的地的曼哈顿距离 小于 逃脱者到达目的地的曼哈顿距离 则逃脱失败
     *
     * @param ghosts
     * @param target
     * @return
     */
    public static boolean escapeGhosts(int[][] ghosts, int[] target) {

        int step = Math.abs(target[0]) + Math.abs(target[1]);
        int people = ghosts.length; //阻碍者人数
        int fail = 0;    //阻碍着能抓到逃脱者人数 即失败次数
        int index = 0;
        while (people > 0 ){
            //坐标系一个点到另一个点的步数
            int ghosts_step = Math.abs(ghosts[index][0] - target[0])
                    + Math.abs(ghosts[index][1] - target[1]);
            if (ghosts_step <= step)
                fail ++;
            index ++;
            people --;
        }
        return fail <= 0;
    }

    public static void main(String[] args) {
//        int[] target = {2, 0};
//        int[][] ghosts = {{1 , 0}};
        int[] target = {8, -10};
        int[][] ghosts = {{1 , 9}, {2, -5}, {3, 8}, {9, 8}, {-1, 3}};
        System.out.println(escapeGhosts(ghosts, target));
    }
}
