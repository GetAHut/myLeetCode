package com.xyt.leecode.structure.linkedLists;

/**
 * @Author: xyt
 * @Description: 单向链表实现
 * @Date: 2021/8/16 15:14
 */
public class LinkedList {

    private ListNode head;  //头节点
    private int size = 0;   //大小

    /**
     * 插入到头部
     * @param data
     */
    public void insertHead(String data){
        ListNode newNode = new ListNode(data);
        newNode.next = head;
        head = newNode;
    }

    /**
     * 插入到中间position位置
     * @param data
     */
    public void insertMid(String data, int position ){

        if (position == 0){
            insertHead(data);
        } else {
            ListNode newNode = new ListNode(data);
            //遍历拿到插入的前节点
            ListNode curr = head;
            for (int i = 0; i < position; i++) {
                curr = curr.next;
            }
            //插入
            newNode.next = curr.next;   //新将新节点指向后节点，保证不丢失后面节点数据
            curr.next = newNode;        //将前节点指向新节点。
        }
    }

    /**
     * 删除头部节点
     */
    public void deleteHead(){
        //
        head = head.next;
    }

    /**
     * 删除中间某节点
     * @param position
     */
    public void deleteMid(int position){

        if (position == 0){
            deleteHead();
        } else {
            ListNode curr = head;
            for (int i = 0; i < position; i++) {
                curr = curr.next;
            }
            //
            curr.next = curr.next.next;
        }
    }

    /**
     * 查找
     * @param position
     * @return
     */
    public String find(int position){
        ListNode curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        return curr.val;
    }

    /**
     * 打印
     */
    public void print(){
        ListNode curr = head;
        while (curr != null){
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
