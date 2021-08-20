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
    private int size;           //栈初始大小

    public LinkedStack(){
        this.head = new ListNode();
        this.tail = null;
        this.n = 0;
    }

    public LinkedStack(int cap){
        this.head = new ListNode();
        this.tail = null;
        this.n = 0;
        this.size = cap;    //元素大小 超过则溢出
    }

    @Override
    public Stack<T> push(T t) {    //入栈 （自动扩容）
        ListNode node = new ListNode(t);
        this.isOverOf();
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

        return isEmpty() ? null : (T)tail.val;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    /**
     * 判断栈空间是否满了 初始化时有size的栈
     * @return
     */
    private void isOverOf(){
        if (size != 0 && n >= size){
            throw new StackOverflowError();
        }
    }

    public static void main(String[] args) {
        Stack<String> stack = new LinkedStack<>();
        stack.push("123");
        stack.push("345");
        stack.push("111");
        String pop = stack.pop();
        System.out.println(pop);
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
