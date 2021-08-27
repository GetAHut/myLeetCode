package com.xyt.leecode.structure.tree;

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
        if (root.getLeft() != null){    //左节点不为空， 以左节点作为根节点递归
            pre(root.getLeft());
        }
        if (root.getRight() != null){   //右节点不为空， 以右节点作为根节点递归
            pre(root.getRight());
        }
    }

    /**
     * 中序遍历  左 根（输出） 右
     * @param root
     */
    public static void in(TreeNode root){
        if (root.getLeft() != null){    //左节点不为空， 以左节点作为根节点递归
            in(root.getLeft());
        }
        print(root);
        if (root.getRight() != null){   //右节点不为空， 以右节点作为根节点递归
            in(root.getRight());
        }
    }

    /**
     * 后序遍历  左 右 根（输出）
     * @param root
     */
    public static void post(TreeNode root){
        if (root.getLeft() != null){    //左节点不为空， 以左节点作为根节点递归
            post(root.getLeft());
        }
        if (root.getRight() != null){   //右节点不为空， 以右节点作为根节点递归
            post(root.getRight());
        }
        print(root);
    }

    /**
     * 输出
     * @param node
     */
    public static void print(TreeNode node){
        System.out.print(node.getVal());
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
    }
}
