package com.xyt.leecode.math;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author: xyt
 * @Description: leetCode 1221 分割平衡字符串 （简单）
 * @Date: 2021/9/7 8:33
 */
public class LeetCode1221 {

    /**
     * 在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。
     * 给你一个平衡字符串s，请你将它分割成尽可能多的平衡字符串。
     * 注意：分割得到的每个字符串都必须是平衡字符串。
     * 返回可以通过分割得到的平衡字符串的 最大数量 。
     * 示例 1：
     *      输入：s = "RLRRLLRLRL"
     *      输出：4
     *      解释：s 可以分割为 "RL"、"RRLL"、"RL"、"RL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
     * 示例 2：
     *      输入：s = "RLLLLRRRLR"
     *      输出：3
     *      解释：s 可以分割为 "RL"、"LLLRRR"、"LR" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
     * 示例 3：
     *      输入：s = "LLLLRRRR"
     *      输出：1
     *      解释：s 只能保持原样 "LLLLRRRR".
     * 示例 4：
     *      输入：s = "RLRRRLLRLL"
     *      输出：2
     *      解释：s 可以分割为 "RL"、"RRRLLRLL" ，每个子字符串中都包含相同数量的 'L' 和 'R' 。
     */

    /**
     * 用栈来实现， 将第一个字符入栈 将另一个字符出栈，栈为空时ans++
     * @param s
     * @return
     */
    public static int balancedStringSplit(String s) {
        Stack<Character> stack = new Stack<>();
        int ans = 0;
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()){
                stack.push(c);  //首字母入栈
                continue;
            }
            if (c == stack.peek()){
                stack.push(c);
            } else {
                stack.pop();
                if (stack.isEmpty())
                    ans ++;
            }
        }

        return ans;
    }

    /**
     * 贪心算法：
     *  如若每次从中间分割成两个字符串，只要一边是平衡字符串
     *  另一边一定是平衡字符串
     * @param s
     * @return
     */
    public static int balancedStringSplitGreed(String s) {
        int ans = 0, d = 0;
        for (int i = 0; i < s.length(); i++) {
            if ('L' == s.charAt(i))
                d ++;
            else
                d --;
            if (d == 0)
                ans ++;
        }
        return ans;
    }
}

class LeetCode1221Test{
    public static void main(String[] args) {
        System.out.println(LeetCode1221.balancedStringSplitGreed("RLRRRLLRLL"));
    }
}
