package com.xyt.leecode.structure.dp;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description: 动态规划  打家劫舍问题：
 * @Date: 2021/11/18 18:38
 */
public class Dp1 {

    /**
     *  topic: 你是一个专业的小偷， 计划偷窃沿街的房屋，每间房内有一定量现金，
     *  影响你偷窃的唯一因素就是相邻的房屋装有报警设备，也就是不能偷窃连续的房屋
     *  在每个房间有定量现金，求能偷窃到的最大金额；
     *   房屋现金数组：[1,2,3,1]  最优解：4
     *   [2, 7, 9, 3, 1] 最优解： 12
     *
     *
     *   思路： 动态规划，
     *   在动态规划之前可以使用递归来实现，
     *   在只有三位数的数组中， 最优解取自， 下标 为1 和 0 + 2 的最大值中；
     *   递归 为  下标为  i - 1 和 (i - 2) + i 的最大值。
     *   递归出口  为  数组 为空， 下标为0 或者为-1
     */

    /**
     * 递归解法
     *  index 为nums.length  即 倒推
     * @param index 下标
     * @param nums 金额数据
     * @return
     */
    public static int maxMoney(int index, int[] nums){

        // 递归出口定义
        if (nums == null || index < 0){
            return 0;
        }
        if (index == 0){
            return nums[0];
        }

        // 递归公式
        return Math.max(maxMoney(index - 1, nums),
                maxMoney(index - 2, nums) + nums[index]);

    }

    /**
     * 动态规划实现
     *  在上个方法 使用递归时 ， 中间进行了多次的重复计算，
     *  因为这些值  是可以提前保存起来，（备忘录）
     *  在动态规划中 最主要的需要找到  dp数组，（状态转移方程）
     *
     *  dp数组 求出金额的最大值，即最优解， 通过 相邻两数就能判断出来。
     *  因此数组只需定义为一维即可， 且，dp[0] dp[1] 均能判断出来
     *
     *
     *  动态规划 最重要三部分
     *      最优子结构； 每一个最优解， 都包含子问题的最优解， 也就是说 在n求得最优解，
     *          则可以推出 n- 1处的最优解。
     *      递推公式（状态转移方程）
     *      重叠子问题。
     * @param nums
     * @return
     */
    public static int maxMoneyDp1(int[] nums){
        // 同样需要出口
        int len = nums.length;
        if (nums == null || len == 0 ) return 0;
        if (len == 1) return nums[0];

        int[] dp = new int[len];
        dp[0] = nums[0]; dp[1] = Math.max(nums[0], nums[1]);

        // 开始递推
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        System.out.println(Arrays.toString(dp));
        return dp[len - 1];
    }

}

class Dp1Test{
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 9, 3, 1};
        System.out.println(Dp1.maxMoney(nums.length - 1, nums));
        System.out.println(Dp1.maxMoneyDp1(nums));
    }
}
