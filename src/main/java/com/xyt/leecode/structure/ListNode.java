package com.xyt.leecode.structure;

import lombok.Data;

/**
 * @Author: xyt
 * @Description: 单向链表
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
}
