package com.xyt.leecode.structure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xyt
 * @Description:  平衡二叉树
 * @Date: 2021/9/9 17:20
 */
public class BalancedBinary {

    /**
     * leetCode 2096 (简单) 平衡二叉树
     * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节
     * 点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     * 示例 1:
     *
     * 给定二叉树 [3,9,20,null,null,15,7]
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回 true 。
     *
     * 示例 2:
     *
     * 给定二叉树 [1,2,2,3,3,null,null,4,4]
     *
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     */


    /**
     * 判断是否是平衡二叉树
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode<Integer> root) {

        return heights(root) >= 0;
    }

    /**
     * 自底向上的递归
     *
     * @param root
     * @return
     */
    private static int heights(TreeNode<Integer> root){
        if (root == null) return 0;

        int leftHeight = heights(root.left);
        int rightHeight = heights(root.right);
        if (leftHeight == -1 || rightHeight == -1 ||Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        } else {
            return Math.max(heights(root.left), heights(root.right)) + 1;
        }
    }

}
