package com.xyt.leecode.interview;

import com.xyt.leecode.structure.interfaces.Stack;
import com.xyt.leecode.structure.stacks.ArrayStack;

import java.util.Scanner;

/**
 * @Author: xyt
 * @Description:    面试题 ： 括号匹配  {}[]()...
 * @Date: 2021/8/17 13:32
 */
public class Brackets {

    /**
     * 括号匹配规则  使用栈实现。
     * @param s
     * @return
     */
    public static boolean isBracketsRules(String s){

        Stack<Character> brackets = new ArrayStack<>(20);
        Character top;

        for (char c : s.toCharArray()) {

            switch (c){
                case '{':
                case '[':
                case '(':
                case '<':
                    //左边括号全部直接push进stack中
                    brackets.push(c);
                    break;
                case '}':
                    top = brackets.pop();
                    if (top == null) return false;
                    if (top == '{'){
                        break;
                    }
                    return false;
                case ']':
                    top = brackets.pop();
                    if (top == null) return false;
                    if (top == '['){
                        break;
                    }
                    return false;
                case ')':
                    top = brackets.pop();
                    if (top == null) return false;
                    if (top == '('){
                        break;
                    }
                    return false;
                case '>':
                    top = brackets.pop();
                    if (top == null) return false;
                    if (top == '<'){
                        break;
                    }
                    return false;
            }
        }

        return brackets.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.next();
            System.out.println(isBracketsRules(s));
        }
    }
}
