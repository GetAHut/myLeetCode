package com.xyt.leecode.math;

import com.xyt.leecode.structure.linkedLists.ListNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: xyt
 * @Description: leetCode 234 回文链表
 * @Date: 2021/8/13 16:34
 */
public class LeetCode234 {

    /**
     * 请判断一个链表是否为回文链表。
     *
     * 示例 1:
     *
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     *
     * 输入: 1->2->2->1
     * 输出: true
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        //思路： 将链表反转 是否与原始相等
        //  链表反转之后 无法保存原始链表， head只有一个节点
        // 此法行不通
        //   官方给出反转链表的另一条思路：
        //      通过快慢指针 找到后半部分链表， 反转后半部分链表， 如果链表为奇数则保留在前半部分，
        // 比较反转后的后半部分链表值，若与前半部分相同则true，并反转会链表
        if (head == null || head.next ==null){
            return true;
        }

        //获取后半部分链表
        ListNode half = halfListNode(head);
        //通过迭代反转后半部分链表
        ListNode secondListNode = InversionLinked.iterator(half);

        //再次通过双指针遍历链表比较值
        ListNode first = head;
        ListNode second = secondListNode;
        boolean ans = true;

        while (ans && second != null){
            if (first.val != second.val) ans = false;

            first = first.next;
            second = second.next;
        }

        //将链表反转回来
        half.next = InversionLinked.iterator(secondListNode);

        return ans;
    }

    /**
     * 获取后半部分链表
     * @param head
     * @return
     */
    private static ListNode halfListNode(ListNode head){
        //通过快慢指针
        ListNode slow = head;
        ListNode fast = head;

        while (slow.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * 思路二：
     *  将链表值存到数组中， 或者 list中， 通过前后指针遍历
     *
     *  提交通过案例84/85
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeArr(ListNode head){

        if (head == null || head.next ==null){
            return true;
        }

        List<String> values = new LinkedList<>();

        //将值存入list中
        while (head != null){ //O(n)
            values.add(head.val);
            head = head.next;
        }

        int front = 0; //前
        int back = values.size() - 1; //后
        while (front < back){//O(n)
            if (!values.get(front).equals(values.get(back))) return false;
            front ++;
            back --;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode("1", null);
        ListNode node4 = new ListNode("2", node5);
        ListNode node3 = new ListNode("2", node4);
        ListNode node2 = new ListNode("1", node3);
//        ListNode node1 = new ListNode("1", node2);

        boolean palindrome = isPalindromeArr(node2);
        boolean palindromeArr = isPalindromeArr(node2);
        System.out.println(palindrome);
        System.out.println(palindromeArr);
    }
}
