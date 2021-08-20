package com.xyt.leecode.structure.queue;

import com.xyt.leecode.structure.interfaces.Queue;

/**
 * @Author: xyt
 * @Description: 数组实现队列 单向队列 （非阻塞）
 * @Date: 2021/8/18 14:16
 */
public class ArrayQueue<E> implements Queue<E> {

    private E[] elements;       //数据
    private int n = 0;          //元素大小
    private int head = 0;       //头
    private int tail = 0;       //尾

    public ArrayQueue(int cap){
        //初始化 数组大小
        this.elements = (E[]) new Object[cap];
        n = cap;
    }

    @Override
    public boolean offer(E e) {     //入队
        //移动元素 最好时间复杂度为O(1) 最坏时间复杂度为O(n)
        this.narrowSpace();
        //如果满了返回false
        if (tail == n) return false;
        else {
            elements[tail] = e;
            tail ++;
            return true;
        }
    }

    @Override
    public boolean add(E e) {   //入队
        //移动元素 最好时间复杂度为O(1) 最坏时间复杂度为O(n)
        this.narrowSpace();
        this.judgeQueue();
        elements[tail] = e;
        tail ++;
        return true;
    }

    @Override
    public E peek() {   //O(1)
        //返回头元素， 不删除
        if (isEmpty()){
            throw new NullPointerException();
        }
        E element = elements[head];
        head ++;
        return element;
    }

    @Override
    public E poll() {       //O(1)
        //返回头元素并删除
        if (isEmpty()){
            throw new NullPointerException();
        }
        E e = elements[head];
        head ++;
        return e;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    /**
     * 判断队列 是否满了
     */
    private void judgeQueue(){
        if (tail == n){
            throw new RuntimeException("队列已满！");
        }
    }

    private boolean isEmpty(){
        //head == tail则为空
        return head == tail;
    }

    /**
     * 由于 头部元素拿出来之后，会将数组前面的空间置空，但存在 以此浪费空间（内存资源）
     * 因此 在队列满了之后，即  在 tail = n && head -tail != 0时 进行向左移动，以此重复利用。
     */
    public void narrowSpace(){
        if (tail == n && head != 0){
            int curr = tail - head;
            for (int i = 0; i < curr; i++) {
                elements[i] = elements[head + i];
            }
            tail = tail - head;
            head = 0;
        }
    }

    /**
     * 获取队列大小
     * @return
     */
    @Override
    public int size(){
        return n;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayQueue<>(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.poll());
        queue.add(4);
        System.out.println(queue.peek());
        queue.offer(5);
        queue.size();
        System.out.println(queue.peek());
    }
}
