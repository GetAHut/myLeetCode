package com.xyt.leecode.structure;

/**
 * @Author: xyt
 * @Description: 双向链表实现
 * @Date: 2021/8/16 15:15
 */
public class DoubleLinkedList {

    private Node head;  //头
    private Node tail;  //㞑

    /**
     * 插入头部
     * @param data
     */
    public void insertHead(int data){
        Node node = new Node(data);

        if (head == null){
            tail = node;
        } else {
            head.pre = node;
            node.next = head;
        }
        head = node;
    }

    /**
     * 从中间某位置插入
     * @param data
     * @param position
     */
    public void insertMid(int data, int position){
        if (position == 0){
            insertHead(data);
        } else {
            Node node = new Node(data);
            Node curr = head;
            //遍历到需要插入的前节点
            for (int i = 0; i < position; i++) {
                curr = curr.next;
            }
            node.next = curr.next;
            curr.next.pre = node;
            node.pre = curr;
            curr.next = node;
        }
    }

    /**
     * 删除头部
     */
    public void deleteHead(){

        if (head != null){
            head = head.next;
            head.pre = null;
        }
    }

    /**
     * 删除中间某一节点
     * @param position
     */
    public void deleteMid(int position){
        if (position == 0){
            deleteHead();
        } else {
            Node curr = head;
            for (int i = 0; i < position; i++) {
                curr = curr.next;
            }
            curr.next = curr.next.next;
            curr.next.next.pre = curr;
        }
    }

    /**
     * 查找
     * @param position
     * @return
     */
    public int find(int position){
        
        Node curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

    //打印
    public void print(){
        Node curr = head;
        while (curr != null){
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
        System.out.println();
    }

}

class Node {

    int value;      //
    Node next;      //后指针
    Node pre;       //前指针

    public Node(int value){
        this.value = value;
        this.next = null;
        this.pre = null;
    }
}
