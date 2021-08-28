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
        if (root.val < val){       //值大于根节点 插入右边
            if (root.right == null){
                root.setRight(new TreeNode<>(val, null, null));
                success = true;
            } else {
                insert(root.right, val);   //递归
            }
        } else {
            if (root.left == null){
                root.setLeft(new TreeNode<>(val, null, null));
                success = true;
            } else {
                insert(root.left, val);
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

        if (root.val > data){  //去左边找
            if (root.left != null){
                find(root.left, data);
            }
        } else if (root.val < data){
            if (root.right != null){
                find(root.right, data);
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
     *      <>参考LeetCode 450 二叉搜索树删除节点</>
     *      https://leetcode-cn.com/problems/delete-node-in-a-bst/solution/shan-chu-er-cha-sou-suo-shu-zhong-de-jie-dian-by-l/
     *
     *  时间复杂度分析： O(log n)
     *      删除过程都是在向左移动或者向右移动H1的高度找到当前节点。
     *      然后在向左或向右移动H2的高度找到前驱、后继节点
     *      H = H1 + H2  H为树的高度、
     *      若为平衡二叉树 则 H = log n；
     *      即最优时间复杂度为O(log n);
     *
     * @param root
     * @param data
     * @return
     */
    public static TreeNode<Integer> remove(TreeNode<Integer> root, int data){
        if (root == null) return null;

        //判断删除左节点 还是右节点 (递归)
        if (root.val > data)    //左节点删除
            root.left = remove(root.left, data);
        else if (root.val < data)   //右节点删除
            root.right = remove(root.right, data);
        else {
            //删除的三种情况
            //删除叶子节点
            if (root.left == null && root.right == null) root = null;
            //删除节点有右节点  则需要将后继节点代替root节点
            else if (root.right != null){
                //将后继节点的值复制给当前节点，
                root.val = successor(root).val;
                //以当前节点的右儿子节点为跟继续递归删除当前节点的后继节点（叶子节点）
                root.right = remove(root.right, root.val);
            } else {
                //以上同理
                root.val = preSuccessor(root).val;
                root.left = remove(root.left, root.val);
            }
        }
        return root;
    }

    /**
     * 查找当前节点的后继节点
     *  先取到当前节点的右节点，然后一直取左节点
     * @param root
     * @return
     */
    public static TreeNode<Integer> successor(TreeNode<Integer> root){
        if (root == null) return null;
        root = root.right;
        while (root.left != null){
            root = root.right;
        }
        return root;
    }

    /**
     * 查找当前节点的前驱节点
     *  先找到左节点， 在一直找右节点
     * @param root
     * @return
     */
    public static TreeNode<Integer> preSuccessor(TreeNode<Integer> root){
        if (root == null) return null;
        root = root.left;
        while (root.right != null){
            root = root.right;
        }
        return root;
    }

}
