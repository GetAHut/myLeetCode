package com.xyt.leecode.math;

import com.xyt.leecode.structure.linkedLists.ListNode;

/**
 * @Author: xyt
 * @Description: leetCode 22 链表中的倒数第k个节点、（简单）
 * @Date: 2021/9/2 9:14
 */
public class LeetCode22 {

    /**
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，
     * 本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有 6 个节点，从头节点开始，
     * 它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
     *
     * 示例：
     *      给定一个链表: 1->2->3->4->5, 和 k = 2.
     *      返回链表 4->5.
     */

    /**
     * 遍历到n- k个节点  n是链表长度
     * @param head
     * @param k
     * @return
     */
    public static ListNode getKthFromEnd(ListNode head, int k) {
        int len = 0;
        ListNode node = null;
        //求链表长度
        for (node = head; node != null; node = node.next) {
            len ++;
        }

        for (node = head; len > k; len--){
            node = node.next;
        }

        return node;
    }

    /**
     * 双指针方式， 后指针先走k个节点， 前后指针之间差k个节点
     * 后指针next为空 则遍历到前指针 直接输出
     * @param head
     * @param k
     * @return
     */
    public static ListNode getKthFromEndDoublePointer(ListNode head, int k) {
        ListNode front = head;
        ListNode back = head;

        while (back != null && k > 0){
            back = back.next;
            k --;
        }

        while (back != null){
            front = front.next;
            back = back.next;
        }
        return front;
    }

}
