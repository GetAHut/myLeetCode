package com.xyt.leecode;

/**
 * @Author: xyt
 * @Description: leetcode 413 等差数列划分
 * @Date: 2021/8/10 15:35
 */
public class ArithmeticProgression {

    /**
     * 根据传入的数组求出 其所有为等差数组的子数组。
     *  示例：
     *   输入：nums = [1,2,3,4]
     *   输出：3
     *   解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
     *
     *   示例 2：
     *
     *   输入：nums = [1]
     *   输出：0
     *
     *   提示：
     *      1 <= nums.length <= 5000
     *      -1000 <= nums[i] <= 1000
     *
     * @param nums
     * @return
     */
    public static int numberOfArithmeticSlices(int[] nums){
        if (nums.length == 1){
            return 0;
        }
        //d是等差数列差值，定义原始差分。 根据循环不断更新
        //设置t初始值
        int d = nums[0] - nums[1], t = 0, ans = 0;

        //数列最小个数为3从3开始计算。
        for (int i = 2; i < nums.length; i++) {

            if (nums[i - 1] - nums[i] == d){
                //使用到了排列组合思想。
                // 比如数组中有一连续的4个数为等差数列 如：1/2/3/4
                //那么 循环到123时 ++t = 1  ans += t = 1
                // 循环到1234时， ++t = 2 ans += t = 3
                //当第5个数不为等差数列， 则直接中断，初始化t和d。
                ++t;
            } else {
                //数列中断则归零。类似从头开始
                d = nums[i - 1] - nums[i];
                t = 0;
            }
            ans += t;
        }

        return ans;
    }

    /**
     * 使用动态规划（DP）解法：
     *      动态规划：详见百度百科： https://baike.baidu.com/item/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92/529408?fr=aladdin
     *          基本思想：适用于dp求解的问题，经分解得到的子问题，往往不是相互独立的
     *                  。如果我们能够保存已解决的子问题的答案，而且能在需要的时候拿到答案
     *                  ，这样可以避免重复计算，节省时间；
     * @param nums
     * @return
     */
    public static int numberOfArithmeticSlicesDp(int[] nums){
        if (nums.length < 3) return 0;

        int dp = 0, ans = 0;

        for (int i = 2; i < nums.length; i++) {
            //动态规则计算，将其分解为多个子问题 求出答案累加
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2])
                dp ++;
            else dp = 0;
            ans += dp;
        }
        return ans;
    }



    public static void main(String[] args) {

        int[] arrs = {0, 5, 2, 3, 4, 5};

        System.out.println(ArithmeticProgression.numberOfArithmeticSlices(arrs));
        System.out.println(ArithmeticProgression.numberOfArithmeticSlicesDp(arrs));
    }


}
