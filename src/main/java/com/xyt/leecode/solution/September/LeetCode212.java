package com.xyt.leecode.solution.September;

import java.util.*;

/**
 * @Author: xyt
 * @Description: leetCode 212 单词搜素 II
 * @Date: 2021/9/16 8:49
 */
public class LeetCode212 {

    /**
     * 给定一个m x n 二维字符网格board和一个单词（字符串）列表 words，
     * 找出所有同时在二维网格和字典中出现的单词。
     * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，
     * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一
     * 个单词中不允许被重复使用。
     *  示例图表
     * @link https://lecnetcode-.com/problems/word-search-ii/
     */

    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * 思路：
     *      枚举所有words[i]首字母为起点的路径，如果相同 则返回words[i]
     *      使用深度优先算法；
     *      官方思路给出 需使用前缀树（Tire） 前缀树 参考中文分词
     *          深度优先算法回溯 + 前缀树
     *          构建前缀树
     *          深度优先遍历表格 board
     * @link https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode-ti500/
     * @param board
     * @param words
     * @return
     */
    public static List<String> findWords(char[][] board, String[] words) {

        //构建前缀树
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        Set<String> ans = new HashSet<>();

        for (int i = 0; i < board.length; i++) {    //图高度
            for (int j = 0; j < board[0].length; j++) { //图宽度
                dfs(board, trie, i, j, ans);
            }
        }

        return new ArrayList<>(ans);
    }

    /**
     * 深度优先算法遍历
     * @param board
     * @param cur
     * @param m
     * @param n
     * @param ans
     */
    private static void dfs(char[][] board, Trie cur, int m, int n, Set<String> ans){
        if (!cur.children.containsKey(board[m][n])) return;

        char c = board[m][n];
        cur = cur.children.get(c);
        if (cur.word != ""){
            ans.add(cur.word);
        }

        //将已经取过的点标记为# 防止重复
        board[m][n] = '#';
        for (int[] dir : dirs) {
            int _m = m + dir[0], _n = n + dir[1];
            if (_m >= 0 && _m < board.length && _n >= 0 && _n < board[0].length){
                dfs(board, cur, _m, _n, ans);
            }
        }
        board[m][n] = c;
    }
}

//前缀树声明
class Trie{
    String word;
    Map<Character, Trie> children;  //子树
    boolean isWord;

    public Trie(){
        this.word = "";
        children = new HashMap<>();
    }

    /**
     * 前缀树 插入
     * @param word
     */
    public void insert(String word){
        Trie cur = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //孩子节点中无 c 则加入
            if (!cur.children.containsKey(c)){
                cur.children.put(c, new Trie());
            }
            cur = cur.children.get(c);
        }
        cur.word = word;
    }
}
