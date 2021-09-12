package com.xyt.leecode.math;

import java.util.Stack;

/**
 * @Author: xyt
 * @Description: leetCode 有效的括号字符串 （中等）
 * @Date: 2021/9/12 11:18
 */
public class LeetCode678 {

    /**
     * 给定一个只包含三种字符的字符串：（，）和 *，写一个函数来检验这个字符串是否为有效字符串。
     * 有效字符串具有如下规则：
     * 任何左括号 (必须有相应的右括号 )。
     * 任何右括号 )必须有相应的左括号 (。
     * 左括号 ( 必须在对应的右括号之前 )。
     * *可以被视为单个右括号 )，或单个左括号 (，或一个空字符串。
     * 一个空字符串也被视为有效字符串。
     * 示例 1:
     *      输入: "()"
     *      输出: True
     * 示例 2:
     *      输入: "(*)"
     *      输出: True
     * 示例 3:
     *      输入: "(*))"
     *      输出: True
     */

    /**
     * 思路：
     *  1. 用栈记录 ( -> （ 一个记录*
     *  2. 如果是） 则去减去 （ ，
     *  3. 如果 记录（的变量还有剩余 则与 *比较  若*数量多 则是合法，
     *  4. 如果）还有剩余 则与*比较， *多则合法。
     * @param s
     * @return
     */
    public static boolean checkValidString(String s) {
        //左括号栈 存放下标
        Stack<Integer> left_bracket = new Stack();
        //星号栈 存放下标
        Stack<Integer> asterisk = new Stack();
        int len = s.length();

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '('){
                //压栈
                left_bracket.add(i);
            } else if (s.charAt(i) == '*'){
                asterisk.add(i);
            }

            if (s.charAt(i) == ')'){
                if (!left_bracket.isEmpty()){
                    left_bracket.pop();
                } else if (!asterisk.isEmpty()){
                    //当左括号栈为空的时候， *表示左括号与之匹配
                    asterisk.pop();
                } else {
                    return false;
                }
            }
        }

        //
        while (!asterisk.isEmpty()){
            Integer pop = asterisk.pop();
            if(!left_bracket.isEmpty()){
                if (left_bracket.pop() > pop){
                    return false;
                }
            }
        }

        return left_bracket.isEmpty();
    }

    /**
     * 思路：使用贪心算法：
     *      1. 如果遇到左括号，则未匹配的左括号数量 + 1
     *      2. 如果遇到右括号， 则需要一个左括号去匹配，因此 未匹配的左括号数量 - 1
     *      3. 如果遇到星号， 则左括号可能 + 1， 可能 - 1 也可能不变
     *  所以：可以在遍历的时候 维护 左括号的 最小值和最大值。且在遇到右括号和星号维护最值
     *      1. 如果遇到左括号， 则最小值、最大值都 + 1
     *      2. 如果遇到右括号， 则最小值、最大值都 - 1
     *      3. 如果遇到星号， 则将最小是 - 1， 最大值 + 1
     *  在任何情况下 ， 未匹配的左括号都不能为负值， 所以当最大值变为负值时，则为无效字符串，
     *  最小值为0时，则不能再减小 == 0
     *  遍历结束， 只有当最小值为0 即 左括号都与右括号匹配， 返回true
     * @param s
     * @return
     */
    public static boolean checkValidStringGreedy(String s) {
        int min = 0, max = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (cur == '('){
                min ++;
                max ++;
            } else if (cur == ')'){
                min = Math.max(min - 1, 0);
                max --;
                if (max < 0){
                    return false;
                }
            } else {    //遇到右括号
                min = Math.max(min - 1, 0);
                max ++;
            }
        }
        return min == 0;
    }
}

class LeetCode678Test{
    public static void main(String[] args) {
        String str = "(*))";
//        String str = "(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())";
        System.out.println(LeetCode678.checkValidString(str));
        System.out.println(LeetCode678.checkValidStringGreedy(str));
    }
}
