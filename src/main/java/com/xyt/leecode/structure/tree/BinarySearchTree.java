package com.xyt.leecode.structure.tree;

/**
 * @Author: xyt
 * @Description: 二叉查找树， 二叉搜索树， 二叉排序树
 * @Date: 2021/8/27 14:47
 */
public class BinarySearchTree<T> {

    //二叉查找树中序遍历  得到的是一个有序的数列。
    //每一个子树根节点，大于左边，小于右边
    //前继节点： 左边第一个小于根节点的节点，此节点一定没有右子树
    //右继节点： 右边第一个大于根节点的节点，此节点一定没有左子树

    /**
     * 插入
     * @param root
     * @param val
     * @return
     */
    public boolean insert(TreeNode<Integer> root, int val){
        boolean success = false;
        if (root == null) return false;
        if (root.getVal() < val){       //值大于根节点 插入右边
            if (root.getRight() == null){
                root.setRight(new TreeNode<>(val, null, null));
                success = true;
            } else {
                insert(root.getRight(), val);   //递归
            }
        } else {
            if (root.getLeft() == null){
                root.setLeft(new TreeNode<>(val, null, null));
                success = true;
            } else {
                insert(root.getLeft(), val);
            }
        }

        return success;
    }

    /**
     * 查找某一节点
     * @param root
     * @param data
     * @return
     */
    public static TreeNode<Integer> find(TreeNode<Integer> root, int data){
        if (root == null) return null;

        if (root.getVal() > data){  //去左边找
            if (root.getLeft() != null){
                find(root.getLeft(), data);
            }
        } else if (root.getVal() < data){
            if (root.getRight() != null){
                find(root.getRight(), data);
            }
        }

        return root;
    }

    /**
     * 删除某一节点
     *  通过前继节点 和后继节点删除
     *      1. 删除的是叶子节点 O(1)
     *      2. 要删除的结点只有一个子树（左子树或者右子树）O(1)
     *      3. 要删除的结点有两颗子树，找后继节点；O(logn)
     * @param root
     * @param data
     * @return
     */
    public static boolean remove(TreeNode<Integer> root, int data){

        //先查找
        TreeNode<Integer> node = find(root, data);

        return false;
    }

}
