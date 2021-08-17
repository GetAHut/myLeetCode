package com.xyt.leecode.structure.interfaces;

/**
 * @Author: xyt
 * @Description: 栈
 * @Date: 2021/8/17 13:15
 */
public interface Stack<T> {

    Stack<T> push(T t);        //入栈

    T pop();            //出栈

    boolean isEmpty();      //判空

    int size();     //大小
}
