package com.xyt.leecode.designMode.fiyweight;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: xyt
 * @Description: 设计模式 - 享元模式
 * @Date: 2021/9/8 17:20
 */
public class FlyWeightTest {
    /**
     * 模式定义：  以运用共享技术有效的支持大量细粒度的对象
     *  String 就是享元模式
     */

    public static void main(String[] args) {
        //tree name xxx is created!
        //只会创建一次
        TreeNode treeNode1 = new TreeNode(3, 5, TreeFactory.getTree("xxx", "sdsadsa"));
        TreeNode treeNode2 = new TreeNode(3, 5, TreeFactory.getTree("xxx", "sdsadsa"));
    }
}

@Data
class Tree{
    private final String name;
    private final String data;
    public Tree(String name, String data){
        System.out.println("tree name " + name + " is created!");
        this.data = data;
        this.name = name;
    }
}

@Data
@AllArgsConstructor
class TreeNode{
    private int x;
    private int y;
    private Tree tree;
}

class TreeFactory{
    //用以创建树 享元模式 对象共享
    private static Map<String, Tree> map = new ConcurrentHashMap<>();

    public static Tree getTree(String name, String data){
        if (map.containsKey(name)){
            return map.get(name);
        }

        //没有则创建
        Tree tree = new Tree(name, data);
        map.put(name, tree);
        return tree;
    }
}
