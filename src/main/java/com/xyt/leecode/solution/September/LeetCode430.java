package com.xyt.leecode.solution.September;

/**
 * @Author: xyt
 * @Description: leetCode 430 扁平化多级双向链表 （中等）
 * @Date: 2021/9/24 8:41
 */
public class LeetCode430 {

    /**
     * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，
     * 可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，
     * 生成多级数据结构，如下面的示例所示。
     * 给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。
     */

    /**
     * 根据题意： child 节点 先遍历， 完了之后 next节点 继续跟上child节点
     *      注意是双向链表
     *      (深度优先算法)
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    /**
     * 深度优先算法  递归
     * @param node
     * @return
     */
    private Node dfs(Node node){
        Node cur = node;
        //记录链表的最后一个节点
        Node last = null;
        while (cur != null){
            //记录下一个节点
            Node next = cur.next;
            //如果有child节点 就去处理child节点。
            if (cur.child != null){
                //递归找到child节点最后一个节点
                Node childLast = dfs(cur.child);

                next = cur.child;
                //将cur与child相连
                cur.next = cur.child;
                cur.child.prev = cur;

                //如果next不为空，就将last与next相连。
                if (next != null){
                    childLast.next = next;
                    next.prev = childLast;
                }

                //将child置空
                cur.child = null;
                last = childLast;
            } else {
                last = cur;
            }
            cur = next; //循环
        }

        return last;
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
