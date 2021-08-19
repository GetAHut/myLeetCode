package com.xyt.leecode.structure.stacks;

import com.xyt.leecode.structure.interfaces.Stack;

/**
 * @Author: xyt
 * @Description: 单向链表实现栈
 * @Date: 2021/8/19 15:48
 */
public class LinkedStack<T> implements Stack<T> {

    private ListNode head;      //头节点
    private ListNode tail;      //尾节点
    private int n;              //元素大小

    public LinkedStack(){
        this.head = new ListNode();
        this.tail = null;
        this.n = 0;
    }

    @Override
    public Stack<T> push(T t) {    //入栈 链表实现的栈 不需要考虑扩容问题
        ListNode node = new ListNode(t);
        if (isEmpty()) {
            head.next = node;
            tail = node;
            n++;
            return null;
        }
        tail.next = node;
        tail = node;
        n ++;
        return null;
    }

    @Override
    public T pop() {        //出栈
        return null;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    public static void main(String[] args) {
        Stack<String> stack = new LinkedStack<>();
        stack.push("123");
        stack.push("345");
        stack.push("111");
    }
}

class ListNode{

    ListNode next;
    Object val;

    public ListNode(ListNode next, Object val){
        this.next = next;
        this.val = val;
    }
    public ListNode(Object val){
        this.val = val;
    }
    public ListNode(){
    }
}
