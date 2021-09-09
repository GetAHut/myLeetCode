package com.xyt.leecode.math;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xyt
 * @Description: 文本左右对齐 leetCode 68(困难)
 * @Date: 2021/9/9 8:40
 */
public class LeetCode68 {

    /**
     * 给定一个单词数组和一个长度maxWidth，重新排版单词，使其成为每行恰好有maxWidth个字符，
     * 且左右两端对齐的文本。
     * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。
     * 必要时可用空格' '填充，使得每行恰好有 maxWidth个字符。
     * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配
     * 则左侧放置的空格数要多于右侧的空格数。
     * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
     * 说明:
     *      单词是指由非空格字符组成的字符序列。
     *      每个单词的长度大于 0，小于等于maxWidth。
     *      输入单词数组 words至少包含一个单词。
     * 示例:
     *
     *      输入:
     *      words = ["This", "is", "an", "example", "of", "text", "justification."]
     *      maxWidth = 16
     *      输出:
     *      [
     *       "This  is     an",
     *       "example of text",
     *       "justification.     "
     *      ]
     *
     * 示例2:
     *      输入:
     *      words = ["What","must","be","acknowledgment","shall","be"]
     *      maxWidth = 16
     *      输出:
     *      [
     *       "What  must   be",
     *       "acknowledgment ",
     *       "shall be    "
     *      ]
     *      解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
     *          因为最后一行应为左对齐，而不是左右两端对齐。
     *           第二行同样为左对齐，这是因为这行只包含一个单词。
     * 示例3:
     *
     * 输入:
     * words = ["Science","is","what","we","understand","well","enough","to","explain",
     *         "to","a","computer.","Art","is","everything","else","we","do"]
     * maxWidth = 20
     * 输出:
     * [
     *  "Science is what   we",
     *  "understand      well",
     *  "enough to explain to",
     *  "a computer. Art   is",
     *  "everything else   we",
     *  "do                  "
     * ]
     */

    /**
     * 贪心算法
     *  1. 当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格；
     *  2. 当前行不是最后一行，且只有一个单词：该单词左对齐，在行末填充空格；
     *  3. 当前行不是最后一行，且不只一个单词
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int right = 0, len = words.length;
        while (true){
            int left = right; //当前行第一个单词在words的位置
            int sumLen = 0;   //表示当前字符串长度
            //求当前行字符串最长有多少， 每个字符串之间至少有一个空格
            while (right < len && sumLen + words[right].length() + right - left <= maxWidth){
                sumLen += words[right ++].length();
            }

            //当前行是最后一行
            if (right == len){
                StringBuffer join = join(words, left, len, " ");
                join.append(blank(maxWidth - join.length()));
                ans.add(join.toString());
                return ans;
            }

            int numWords = right - left;        //每行的单词数
            int numSpaces = maxWidth - sumLen;  //每行剩下的位数

            //当前行只有一个单词，左对齐，剩下空格填充
            if (numWords == 1){
                StringBuffer sb = new StringBuffer(words[left]);
                sb.append(blank(numSpaces));
                ans.add(sb.toString());
                continue;
            }

            //当前行不是一个单词
            //单词之间应有avgSpace个空格
            int avgSpace = numSpaces / (numWords - 1);
            //平均分配后多出来的空格，应分配在前extraSpace单词之间。
            int extraSpace = numSpaces % (numWords - 1);
            StringBuffer sb = new StringBuffer();
            //拼接额外加一个空格的单词
            sb.append(join(words, left, left + extraSpace + 1, blank(avgSpace + 1)));
            sb.append(blank(avgSpace));
            //拼接剩下的单词
            sb.append(join(words, left + extraSpace + 1, right, blank(avgSpace)));
            ans.add(sb.toString());
        }
    }

    //返回有n个长度的空格字符串
    private static String blank(int n){
        StringBuffer sb = new StringBuffer();
        while (n > 0){
            sb.append(' ');
            n --;
        }
        return sb.toString();
    }

    //返回用sep拼接[left, right)范围内的字符串
    private static StringBuffer join(String[] words, int left, int right, String sep){
        StringBuffer sb = new StringBuffer(words[left]);
        for (int i = left + 1; i < right; ++i) {
            sb.append(sep);
            sb.append(words[i]);
        }
        return sb;
    }
}

class LeetCode68Test{

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> list = LeetCode68.fullJustify(words, 16);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
