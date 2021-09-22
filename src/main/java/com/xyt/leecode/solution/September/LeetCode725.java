package com.xyt.leecode.solution.September;

/**
 * @Author: xyt
 * @Description: leetCode 725 拆分链表 (中等)
 * @Date: 2021/9/22 9:10
 */
public class LeetCode725 {

    /**
     * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k
     * 个连续的部分。
     * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。
     * 这可能会导致有些部分为 null 。
     * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该
     * 大于或等于排在后面的长度。
     * 返回一个由上述 k 部分组成的数组。
     */

    /**
     * 两次循环 ，第一次循环 获取链表长度
     *  第二次根据长度拆分
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode[] splitListToParts(ListNode head, int k) {

        int len = 0;
        ListNode temp = head;
        while (temp != null){
            len ++;
            temp = temp.next;
        }

        //前_length个长度为length + 1, 其余的都是length；
        int length = len / k, _length = len % k;
        ListNode[] parts = new ListNode[k];
        ListNode curr = head;
        for (int i = 0; i < k && curr != null; i++) {
            parts[i] = curr;
            int partSize = length + (i < _length ? 1 : 0);
            for (int j = 1; j < partSize; j++) {
                curr = curr.next;
            }

            ListNode next = curr.next;
            curr.next = null;
            curr = next;
        }

        return parts;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
