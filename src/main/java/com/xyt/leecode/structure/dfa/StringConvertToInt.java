package com.xyt.leecode.structure.dfa;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xyt
 * @Description: leetCode 8 字符串转整数
 * @Date: 2021/11/8 8:32
 */
public class StringConvertToInt {

    /**
     * @describe:
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     *
     * 函数 myAtoi(string s) 的算法如下：
     *
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * 注意：
     *
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     */

    /**
     * DFA 自动机、确定有限机
     *  DFA： 表示一个有限状态集合和一些从一个状态通向另一个状态的边，每条边标记为一个符号，其中一个是初态某些状态是终态，
     *  但是不同 与不确定的有限自动机，DFA不会从同一状态出发至两条边表示的有相同的符号
     * https://baike.baidu.com/item/%E7%A1%AE%E5%AE%9A%E6%9C%89%E9%99%90%E7%8A%B6%E6%80%81%E8%87%AA%E5%8A%A8%E6%9C%BA%E6%9C%80%E5%B0%8F%E5%8C%96/22735774?fr=aladdin     * @param s
     * @return
     */
    public int myAtoi(String s) {
        Automaton automaton = new Automaton();
        for (int i = 0; i < s.length(); i++) {
            automaton.get(s.charAt(i));
        }

        return (int)(automaton.sign * automaton.ans);
    }

    public static void main(String[] args) {
        StringConvertToInt so = new StringConvertToInt();
        System.out.println(so.myAtoi("42"));
    }
}

class Automaton {
    public int sign = 1;    //用以标记+-
    public long ans = 0;     //result
    private String start = "start";

    //设置DFA四个状态 start signed in_number end 匹配 ' ', +-, in_number , end
    // 根据条件规则： 省去前导空格， 如果是+- 或者数字 则到达in_number状态， 否则进入end
    // 定义状态规则
    private static Map<String, String[]> status = new HashMap<String, String[]>(){{
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
    }};
//    static {
//        //                                 ' '      +-         number      end
//        status.put("start", new String[]{"start", "signed", "in_number", "end"});
//        status.put("signed", new String[]{"end", "end", "in_number", "end"});
//        status.put("in_number", new String[]{"end", "end", "in_number", "end"});
//        status.put("end", new String[]{"end", "end", "end", "end"});
//    }

    public void get(char c){
        start = status.get(start)[convertChar(c)];
        if ("in_number".equals(start)){
            //判断是否越界 边界处理
            //数字推入
            ans = ans * 10 + c - '0';
            ans = sign == 1 ? Math.min(ans, (long)Integer.MAX_VALUE) : Math.min(ans, -(long)Integer.MIN_VALUE);
        } else if ("signed".equals(start)){
            // +-处理
            sign = c == '+' ? 1 : -1;
        }
    }

    private int convertChar(char c){
        if (c == ' '){
            return 0;
        }
        if (c == '+' || c == '-'){
            return 1;
        }
        if (Character.isDigit(c)){
            return 2;
        }
        return 3;
    }
}
