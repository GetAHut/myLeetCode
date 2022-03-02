package com.xyt.leecode.solution;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 最不经常使用
 *
 *  双Hash表实现
 *
 * @Author: xyt
 * @Date: 2022/3/2 15:57
 * @version: 1.0.0
 */
public class LFUCache {

    /** hash表存放节点 */
    class Node{
        /** index 使用次数索引序列 */
        int key, value, index;
        public Node(int key, int value, int index){
            this.key = key;
            this.value = value;
            this.index = index;
        }
    }

    /** 定义最小访问次数，容量 */
    int minIndex, capacity;
    Map<Integer, Node> keyMap;
    Map<Integer, LinkedList<Node>> indexMap;

    public LFUCache(int capacity){
        minIndex = 0;
        this.capacity = capacity;
        keyMap = new HashMap<>();
        indexMap = new HashMap<>();
    }

    public int get(int key){
        if (capacity == 0){
            return -1;
        }
        if (!keyMap.containsKey(key)){
            return -1;
        }
        // 1.  从keyMap中获取Node
        Node node = keyMap.get(key);
        int val = node.value;
        int index = node.index;
        // 2. 从indexMap中获取LinkedList
        indexMap.get(index).remove(node);
        // 3. 如果链表为空 需要从map中删除， 并且判断更新minIndex
        if (indexMap.get(index).size() == 0){
            indexMap.remove(index);
            if (minIndex == index){
                minIndex += 1;
            }
        }
        // 4. 如果链表不为空，则将其移动到index + 1的位置中
        LinkedList<Node> next = indexMap.getOrDefault(index + 1, new LinkedList<Node>());
        // 5. 保证顺序，使用头插法
        next.offerFirst(new Node(key, val, index + 1));
        // 6. 插入 indexMap
        indexMap.put(index + 1, next);
        // 7. 更新到keyMap
        keyMap.put(key, indexMap.get(index + 1).peekFirst());
        return val;
    }

    public void put(int key, int value){
        if (capacity == 0){
            return;
        }

        if (!keyMap.containsKey(key)){
            // 1. 缓存已满 需要删除最不经常使用的
            if (keyMap.size() == capacity){
                // 2. 根据 minIndex 需要删除 indexMap(minIndex)链表最后的节点
                Node last = indexMap.get(minIndex).peekLast();
                // 3. 删除keyMap中的节点
                keyMap.remove(last.key);
                // 4. 删除indexMap(minIndex) 链表的最后节点
                indexMap.get(minIndex).pollLast();
                if (indexMap.get(minIndex).size() == 0){
                    // 5. 链表为空则直接删除
                    indexMap.remove(minIndex);
                }
            }
            // 6. 插入当前新的key
            LinkedList<Node> first = indexMap.getOrDefault(1, new LinkedList<Node>());
            first.offerFirst(new Node(key, value, 1));
            indexMap.put(1, first);
            keyMap.put(key, indexMap.get(1).peekFirst());
            minIndex = 1;
        } else {
            // 与get操作基本一致 更新值 并且保证顺序
            Node exist = keyMap.get(key);
            int index = exist.index;
            indexMap.get(index).remove(exist);
            if (indexMap.get(index).size() == 0){
                indexMap.remove(index);
                if (index == minIndex){
                    minIndex += 1;
                }
            }
            // 插入 下一节点的位置
            LinkedList<Node> next = indexMap.getOrDefault(index + 1, new LinkedList<>());
            next.offerFirst(new Node(key, value, index + 1));
            indexMap.put(index + 1, next);
            keyMap.put(key, indexMap.get(index + 1).peekFirst());
        }
    }

}
