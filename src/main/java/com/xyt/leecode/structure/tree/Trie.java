package com.xyt.leecode.structure.tree;

/**
 * @Author: xyt
 * @Description: 前缀树:用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * @Date: 2021/9/16 12:38
 */
public class Trie {

    /**
     * 中文分词 搜索引擎 字典树
     * 数组实现
     * @link https://leetcode-cn.com/problems/implement-trie-prefix-tree/submissions/
     */

    private Trie[] children;    //孩子节点，存放字符串 所有是26字母 通过数组存放
    private boolean isEnd;      //表示字符串结尾

    /** Initialize your data structure here. */
    public Trie() {
        //初始化
        this.children = new Trie[26];
        this.isEnd = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie cur = this;    //拿到当前节点
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';    //数组存放位置指针
            if (cur.children[index] == null){   //index位置为空，则存入
                cur.children[index] = new Trie();
            }
            cur = cur.children[index];  //有值直接拿出来
        }
        cur.isEnd = true;       //遍历最后标记为结尾。
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        //查找
        Trie trie = searchPrefix(word);
        //节点不为空，且是结尾。
        return trie != null && trie.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        //如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false
        return searchPrefix(prefix) != null;
    }

    /**
     * 通过前缀查询
     * @param prefix
     * @return
     */
    private Trie searchPrefix(String prefix){
        Trie cur = this;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int index = c - 'a';
            if (cur.children[index] == null){
                return null;
            }
            cur = cur.children[index];
        }
        return cur;
    }
}

class TireTest{

    public static void main(String[] args) {
        String[] words = {"Trie", "insert", "search", "search", "startsWith", "insert", "search"};
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }


    }
}
