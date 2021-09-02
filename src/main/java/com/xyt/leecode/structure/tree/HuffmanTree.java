package com.xyt.leecode.structure.tree;

import java.util.*;

/**
 * @Author: xyt
 * @Description:  哈夫曼树 (最优二叉树)
 * @Date: 2021/9/2 13:11
 */
public class HuffmanTree {

    //哈夫曼数据结构定义
    HfmNode root;   //根节点
    List<HfmNode> leaf; //叶子节点。 因为叶子节点存放的是字符，求编码 需要取出所有叶子节点
    Map<Character, Integer> weights;  //每一个字符对应的权重

    public HuffmanTree(Map<Character, Integer> weights){

        //初始化
        this.weights = weights;
        leaf = new ArrayList<>();
    }

    /**
     * 构建哈夫曼树
     * @return
     */
    public HfmNode createTree(){
        //拿出权重map中所有的字符点（key）
        Character[] keys = weights.keySet().toArray(new Character[0]);
        //构建优先队列
        Queue<HfmNode> queue = new PriorityQueue<>();
        //将keys中所有的点遍历，生成HfmNode
        for (Character key : keys) {
            HfmNode node = new HfmNode();
            node.chars = key.toString();    //点
            node.weight = weights.get(key); //权重
            queue.add(node);                //加入到优先队列
            leaf.add(node);                 //加入到叶子节点
        }

        //队列大小， 节点个数
        int len = queue.size();
        //每次找最小的两个点合并， 所以循环次数为len - 1次， 最后一个点不需要循环
        for (int i = 1; i <= len - 1; i++) {
            HfmNode left = queue.poll();    //第一次从队列拿节点 为左节点
            HfmNode right = queue.poll();   //第二次拿 要比第一次大，作为右节点

            //找到两个节点后 需要合并成一个节点
            HfmNode newNode = new HfmNode();
            newNode.weight = left.weight + right.weight;    //权重赋值
            newNode.left = left;
            newNode.right = right;

            //防止遍历 父节点丢失。
            left.parent = newNode;
            right.parent = newNode;
            //重新放入优先队列
            queue.add(newNode);
        }
        return queue.poll();        //队列最后的节点为跟节点
    }

    /**
     * 对叶子节点编码
     * @return
     */
    public Map<Character, String> code(){
        Map<Character, String> codeBook = new HashMap<>();
        //对叶子节点list遍历
        for (HfmNode node : leaf) {
            char c = node.chars.charAt(0);
            HfmNode cur = node;
            String code = "";   //编码
            do {
                if (cur.parent != null && cur.parent.left == cur){
                    //cur.parent.left == cur 判断自己是否是左子树
                    code = "0" + code;  //左子树 0 +
                } else {
                    code = "1" + code; //右子树 1 +
                }
                cur = cur.parent;
            } while (cur.parent != null); // cur.parent == null表示结束

            codeBook.put(c, code);  //存入map中
            System.out.println(c + ":" + code);
        }

        return codeBook;
    }

    /**
     * 解码
     * @param password
     * @return
     */
    public String deCode(String password){

        return null;
    }

    /**
     * 加密
     * @param password
     * @return
     */
    public String enCode(String password){
        StringBuffer code = new StringBuffer();
        Map<Character, String> codeBook = code();
        for (char c : password.toCharArray()) {
            code.append(codeBook.get(c));
        }
        return code.toString();
    }

    public static void main(String[] args) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('a', 3);
        map.put('b', 24);
        map.put('c', 6);
        map.put('d', 20);
        map.put('e', 34);
        map.put('f', 4);
        map.put('g', 12);
        HuffmanTree tree = new HuffmanTree(map);
        tree.createTree();
        Map<Character, String> code = tree.code();
        System.out.println(tree.enCode("abc"));
    }
}

/**
 * 哈夫曼树节点
 */
class HfmNode implements Comparable<HfmNode>{

    /**
     * 需要对权重进行排序， 取最小的两个值 作为左右子树，
     * 求和后在放入权重中 取最小两个数
     *  插入排序可以解决问题；
     *  但是可以使用优先队列。
     *      优先队列不允许空值，而且不支持 不可比较的对象，比如用户自定义的类。
     *      优先队列要求对象实现 Comparable接口，并且在排序时会优先按照优先级处理数据
     */

    String chars;   //节点里面的字符
    int weight;     //每一个节点中的字符对应的权重。
    HfmNode left;   //左节点
    HfmNode right;  //右节点
    HfmNode parent; //父节点，用以向上遍历（从叶子节点遍历到根节点）

    @Override
    public int compareTo(HfmNode hfmNode) {
        //从小到大排序
        return this.weight - hfmNode.weight;
    }
}
