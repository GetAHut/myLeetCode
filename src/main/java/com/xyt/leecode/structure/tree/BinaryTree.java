package com.xyt.leecode.structure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: xyt
 * @Description:  二叉树 （链表结构）
 * @Date: 2021/8/26 15:15
 */
public class BinaryTree {

    //二叉树遍历  前中后序  层序

    /**
     * 前序遍历： 根（输出） 左  右
     * @param root
     */
    public static void pre(TreeNode root){
        print(root);
        if (root.left != null){    //左节点不为空， 以左节点作为根节点递归
            pre(root.left);
        }
        if (root.right != null){   //右节点不为空， 以右节点作为根节点递归
            pre(root.right);
        }
    }

    /**
     * 中序遍历  左 根（输出） 右
     * @param root
     */
    public static void in(TreeNode root){
        if (root.left != null){    //左节点不为空， 以左节点作为根节点递归
            in(root.left);
        }
        print(root);
        if (root.right != null){   //右节点不为空， 以右节点作为根节点递归
            in(root.right);
        }
    }

    /**
     * 后序遍历  左 右 根（输出）
     * @param root
     */
    public static void post(TreeNode root){
        if (root.left != null){    //左节点不为空， 以左节点作为根节点递归
            post(root.left);
        }
        if (root.right != null){   //右节点不为空， 以右节点作为根节点递归
            post(root.right);
        }
        print(root);
    }

    /**
     * 层序遍历   可以使用 数组、队列实现
     * @param root
     * @return
     */
    public static void traversal(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            print(poll);
            if (poll.left != null){
                queue.offer(poll.left);
            }
            if (poll.right != null){
                queue.offer(poll.right);
            }
        }
    }

    /**
     * 输出
     * @param node
     */
    public static void print(TreeNode node){
        System.out.print(node.val);
    }


    public static void main(String[] args) {
        TreeNode<Character> D = new TreeNode<>('D', null, null);
        TreeNode<Character> H = new TreeNode<>('H', null, null);
        TreeNode<Character> K = new TreeNode<>('K', null, null);
        TreeNode<Character> C = new TreeNode<>('C', D, null);
        TreeNode<Character> B = new TreeNode<>('B', null, C);
        TreeNode<Character> G = new TreeNode<>('G', H, K);
        TreeNode<Character> F = new TreeNode<>('F', G, null);
        TreeNode<Character> E = new TreeNode<>('E', null, F);
        TreeNode<Character> A = new TreeNode<>('A', B, E);

        BinaryTree.pre(A);
        System.out.println();
        BinaryTree.in(A);
        System.out.println();
        BinaryTree.post(A);
        System.out.println();
        BinaryTree.traversal(A);
    }
}
