package com.xyt.leecode.math;

/**
 * @Author: xyt
 * @Description:    leetCode 压缩字符串 443 （中等）
 * @Date: 2021/8/21 14:27
 */
public class LeetCode443 {

    /**
     * 给你一个字符数组 chars ，请使用下述算法压缩：
     * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
     * 如果这一组长度为 1 ，则将字符追加到 s 中。
     * 否则，需要向 s 追加字符，后跟这一组的长度。
     * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。
     * 需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
     * 请在 修改完输入数组后 ，返回该数组的新长度。
     * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
     *
     * 示例 1：
     *      输入：chars = ['a','a','b','b','c','c','c']
     *      输出：返回 6 ，输入数组的前 6 个字符应该是：['a','2','b','2','c','3']
     *      解释：
     *      'aa' 被 'a2' 替代。'bb' 被 'b2' 替代。'ccc' 被 'c3' 替代。
     * 示例 2：
     *      输入：chars = ['a']
     *      输出：返回 1 ，输入数组的前 1 个字符应该是：['a']
     *      解释：
     *      没有任何字符串被替代。
     * 示例 3：
     *      输入：chars = ['a','b','b','b','b','b','b','b','b','b','b','b','b']
     *      输出：返回 4 ，输入数组的前 4 个字符应该是：['a','b','1','2']。
     *      解释：
     *      由于字符 'a' 不重复，所以不会被压缩。'bbbbbbbbbbbb' 被 “b12” 替代。
     *      注意每个数字在数组中都有它自己的位置。
     */

    /**
     * 思路：
     *  字符连续出现一次 不加位数，
     *  出现多次 则在数组中增加 次数/(10^次数) 位。
     *      (错误) ： 曲解了题意  只求出了最后的数组长度， 没有压缩数组
     * @param chars
     * @return
     */
    public static int compress(char[] chars) {

        if (chars.length == 1) return 1;

        //chars遍历指针 ans 最终返回压缩数组长度
        int front = 0, back = 1, ans = 0;

        while (back < chars.length - 1){
            //当遇到不同字符的时候
            if (chars[front] != chars[back]){
                ans += charNums(front, back);
                front = back;
            }
            back ++;
        }
        //最后一段字符全部相等
        ans += charNums(front, back);
        return ans;
    }

    private static int charNums( int front, int back){
        int temp = back - front;
        if (temp > 1 && temp < 10){
            return 2;
        } else if (temp >= 10){
            while (((back - front) / (Math.pow(10, temp))) < 1){
                temp --;
            }
            return temp + 2;
        }
        return 1;
    }

    /**
     * 官方解答： 双指针 类似滑动窗口
     * @param chars
     * @return
     */
    public static int compressOfficial(char[] chars){

        //双指针， ans 为最后数组长度
        int len = chars.length, front = 0, back = 0, ans = 0;

        for (; back < len; back++) {
            if ((back == len - 1) || chars[back] != chars[back + 1]){
                chars[ans ++] = chars[back];
                int temp = back - front + 1;    //相同字符长度
                if (temp > 1) { //temp = 1 已经写入了字符
                    //使用短除法 求位数
                    int curr = ans;
                    while (temp > 0){
                        // 千 百 个 需要反转
                        chars[ans ++] = (char) (temp % 10 + '0');
                        temp /= 10;
                    }
                    reserve(chars, curr, ans - 1);
                }
                front = back + 1;
            }
        }
        return ans;
    }

    /**
     * 反转数组
     * @param chars
     * @param left
     * @param right
     */
    private static void reserve(char[] chars, int left, int right){
        while (left < right){
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left ++;
            right --;
        }
    }


    public static void main(String[] args) {
//        char[] arr = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        char[] arr = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        System.out.println(compress(arr));
        System.out.println(compressOfficial(arr));
    }
}
