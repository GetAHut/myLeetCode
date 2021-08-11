package com.xyt.leecode.math;

import com.xyt.leecode.structure.ListNode;

/**
 * @Author: xyt
 * @Description: leetCode  链表反转
 * @Date: 2021/8/11 15:30
 */
public class InversionLinked {

    /**
     * 迭代方式 反转链表
     *  由于链表的特性 断开next节点 会丢失后面节点信息，
     *  所以 先将next节点信息临时存储；
     *  根据节点变化状态 赋值即可。
     * @param head
     * @return
     */
    public static ListNode iterator(ListNode head){

        //前节点存储
        ListNode prev = null;
        //后一节点存储
        ListNode next;
        //当前节点
        ListNode curr = head;

        while (curr != null){
            next = curr.next;
            curr.next = prev;

            //将后一节点作为当前节点 迭代
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 使用递归的方式反转链表
     *  思路： 递归思想， 将链表拆分为2个一组，将head.next.next = head 指向第一个node
     *          再将 head.next = null 断开
     *        因为断开节点后会丢失后一个节点信息。
     *        所以可以倒叙开始递归。
     *
     * @param head 头节点
     */
    public static ListNode recursion(ListNode head){

        //递归出口
        if (head == null || head.next == null){
            return head;
        }

        //先递归至最后节点开始
        ListNode recursion = recursion(head.next);
        head.next.next = head;
        head.next = null;
        return recursion;
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode("5", null);
        ListNode node4 = new ListNode("4", node5);
        ListNode node3 = new ListNode("3", node4);
        ListNode node2 = new ListNode("2", node3);
        ListNode node1 = new ListNode("1", node2);
//        ListNode iterator = iterator(node1);
        ListNode recursion = recursion(node1);
        System.out.println(recursion);
    }
}
