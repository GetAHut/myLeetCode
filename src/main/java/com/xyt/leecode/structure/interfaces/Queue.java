package com.xyt.leecode.structure.interfaces;

/**
 * @Author: xyt
 * @Description: 队列
 * @Date: 2021/8/18 14:08
 */
public interface Queue<E> {

    /**
     *  添加元素到队列， 如果满了 返回false
     * @param e
     * @return
     */
    boolean offer(E e);

    /**
     * 添加元素到队列，
     * @param e
     * @return
     */
    boolean add(E e);

    /**
     * 返回队列头部元素， 不会删除
     * @return
     */
    E peek();

    /**
     * 返回队列头部元素，并删除
     * @return
     */
    E poll();

    /**
     * 移除并返回头部元素
     * @return
     */
    E remove();

    /**
     * 返回头部元素 ，不会删除
     * @return
     */
    E element();
}
