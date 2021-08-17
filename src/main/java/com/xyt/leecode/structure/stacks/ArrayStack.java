package com.xyt.leecode.structure.stacks;

import com.xyt.leecode.structure.interfaces.Stack;

/**
 * @Author: xyt
 * @Description:  数组实现的栈
 * @Date: 2021/8/17 13:20
 */
public class ArrayStack<T> implements Stack<T> {

    private int size = 0;       //初始大小
    private T[] t_arr;

    public ArrayStack(int cap){
        //初始化 默认数组
        t_arr = (T[]) new Object[cap];
    }

    @Override
    public Stack<T> push(T t) {     //O(1)

        judgeSize();
        t_arr[size ++] = t;

        return null;
    }

    @Override
    public T pop() {     //O(1)

        if (size == 0){
            return null;
        }

        judgeSize();
        //--n 和 n--区别， 前者先减了在用， 后者 用了再减
        T t = t_arr[-- size];
        t_arr[size] = null;
        return t;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 判断是否需要扩容
     */
    public void judgeSize(){
        if (size >= t_arr.length){
            //两倍扩容
            reSize(t_arr.length * 2);
        } else if (size < t_arr.length / 2){   //回收多余空间
            reSize(t_arr.length / 2);
        }
    }

    /**
     * 扩容
     * @param reSize
     */
    private void reSize(int reSize){
        T[] temp = (T[]) new Object[reSize];
        for (int i = 0; i < size; i++) {
            temp[i] = t_arr[i];
        }
        t_arr = temp;
    }
}
