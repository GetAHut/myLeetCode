package com.xyt.leecode.structure.hashandbit;

/**
 * @Author: xyt
 * @Description: 位图
 * @Date: 2021/9/9 16:35
 */
public class BitMap {

    private byte[] bits;    //使用byte存储 一个byte存放8个bit。 int 存放32位
    private int max;        //表示最大的那个数

    public BitMap(int max){
        this.max = max;
        //max / 8 + 1
        //最大的数 / 每一个byte中存放的个数 + 1
        bits = new byte[(max >> 3) + 1];
    }

    //将n存入bit 用1标识位
    public void add(int n){
        int byteIndex = n >> 3;     //找到byte数组中哪一个
        // % 可以使用&运算
        // a % b = a & (b - 1) 前提是b是 2的n次方
//        int loc = n % 8;       //找到需要标记的bit
        int loc = n & (8 - 1);
        bits[byteIndex] |= 1 << loc;   //将当前bit位和1 或运算。
    }

    //查找
    public boolean find(int n){
        int byteIndex = n >> 3;
        int loc = n & (8 - 1);
        //判断当前位置为1 则存在, 如果那个位置是0 则依旧是0，
        // 1 << loc 1左移loc位。 然后与bits[byteIndex] 做&运算
        //&运算 都是1才为1。 |运算有一个1就为1。 ^运算，不同为1；
        int flag = bits[byteIndex] & (1 << loc);
        if (flag == 0) return false;
        return true;
    }

    //删除 n
    public void remove(int n){
        if (find(n)){
            int byteIndex = n >> 3;
            int loc = n & (8 - 1);
            //^运算相同则为0
            //先或运算设置成1
            bits[byteIndex] |= (1 << loc);
            //在 & 1 = 0
            bits[byteIndex] ^= (1 << loc);
        }
    }
}

class BitMapTest{

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(1000000);
        bitMap.add(2);
        bitMap.add(3);
        bitMap.add(100);
        System.out.println(bitMap.find(3));
        System.out.println(bitMap.find(15));
        bitMap.remove(3);
        System.out.println(bitMap.find(3));
    }
}
