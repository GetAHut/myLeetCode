package com.xyt.leecode.structure.tree;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: xyt
 * @Description:
 * @Date: 2021/8/26 15:16
 */
@Data
@AllArgsConstructor
public class TreeNode<T> {

    private T val;              //value
    private TreeNode<T> left;   //左节点
    private TreeNode<T> right;  //右节点

}
