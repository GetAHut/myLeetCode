package com.xyt.leecode.solution.September;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xyt
 * @Description: leetCode 437 路径总和III （中等）
 * @Date: 2021/9/28 8:36
 */
public class LeetCode437 {

    /**
     * 给定一个二叉树的根节点 root，和一个整数 targetSum ，
     * 求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     *
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的
     * （只能从父节点到子节点）。
     */

    /**
     *
     * 深度优先算法， 递归求左右子树的值 是否还满足target
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;

        int ans = getLength(root, targetSum);
        ans += pathSum(root.right, targetSum);
        ans += pathSum(root.left, targetSum);

        return ans;
    }

    private int getLength(TreeNode root, int targetSum){
        int ans = 0;
        if (root == null) return 0;
        int val = root.val;

        if (val == targetSum) ans ++;

        //递归 targetSum - val减法处理。
        ans += getLength(root.left, targetSum - val);
        ans += getLength(root.right, targetSum -val);

        return ans;
    }

    /**
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSumPre(TreeNode root, int targetSum){
        HashMap<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    /**
     * 深度优先算法
     * @param root
     * @param prefix
     * @param cur
     * @param targetSum
     * @return
     */
    private int dfs(TreeNode root, Map<Integer, Integer> prefix, int cur, int targetSum){
        if (root == null) return 0;

        int ans = 0;
        cur += root.val;

        ans = prefix.getOrDefault(cur - targetSum, 0);
        prefix.put(cur, prefix.getOrDefault(cur, 0) + 1);
        ans += dfs(root.left, prefix, cur, targetSum);
        ans += dfs(root.right, prefix, cur, targetSum);
        prefix.put(cur, prefix.getOrDefault(cur, 0) - 1);

        return ans;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
