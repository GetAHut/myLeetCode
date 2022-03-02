package com.xyt.leecode.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xyt
 * @Desiciption: LRU 最近最少使用缓存算法实现   不考虑线程安全
 * leetCode 146
 * @Date: 2022/2/24 12:44
 * @version: 1.0.0
 */
public class LRUCache {

    /**
     *  链表节点
     */
    class Node{
        int key;
        int value;
        Node pre;
        Node next;
    }

    /** 定义链表头尾节点 */
    private Map<Integer, Node> cache = new HashMap<>();
    /** 定义链表头尾节点 */
    private Node head, tail;

    public LRUCache(int capacity){
        head = new Node();
        // 在head中存放容量
        head.key = capacity;
        head.value = 0;
        head.pre = null;
        tail = new Node();
        tail.pre = head;
        tail.next = null;
        head.next = tail;
    }

    /**
     * 头插法
     * @param node
     */
    private void addHead(Node node){
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    /**
     * 将节点从链表删除
     * @param node
     */
    private void remove(Node node){
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
    }

    /**
     * 将使用的节点移动到头部
     * @param node
     */
    private void moveToHead(Node node){
        this.remove(node);
        this.addHead(node);
    }

    /**
     * 移除尾节点
     */
    private Node removeTail(){
        Node r = tail.pre;
        this.remove(r);
        return r;
    }

    /**
     * 拿缓存 需要将其加入头节点
     * @param key
     * @return
     */
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null){
            return -1;
        }
        this.moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null){
            // 存储
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            cache.put(key, newNode);
            this.addHead(newNode);
            // 容量超过了 需要删除尾节点
            if (++head.value > head.key){
                Node lost = this.removeTail();
                this.cache.remove(lost.key);
                --head.value;
            }
        } else {
            // 表示当前节点被使用 移动到头部
            // 更新节点数据
            node.value = value;
            this.moveToHead(node);
        }
    }
}

