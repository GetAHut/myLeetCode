package com.xyt.leecode.structure;

import lombok.Data;

/**
 * @Author: xyt
 * @Description: 链表node节点
 * @Date: 2021/8/11 15:28
 */
@Data
public class ListNode {

    public ListNode next;
    public String val;

    public ListNode(String val, ListNode node){
        this.val = val;
        this.next = node;
    }

    public ListNode(String val) {
        this.val = val;
    }
}
