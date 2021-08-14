package com.xyt.leecode.structure;

/**
 * @Author: xyt
 * @Description: 自定义实现数组
 * @Date: 2021/8/13 14:46
 */
public class Array {

    private int size;       //数组大小
    private int data[];     //数据
    private int index;      //下标

    public Array(int size){
        this.size = size;
        data = new int[size];   //初始化内存大小
        index = 0;
    }

    //数组输出
    public void print(){
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    /**
     * 添加元素
     * @param sub 下标
     * @param val 值
     */
    public void add(int sub, int val){
        //判断是否越界 这里没有做  扩容系数处理
        if (index ++ < size){
            //从插入位置开始向后挪一位
            //从最后一位开始操作， 避免覆盖数据
            for (int i = size - 1; i > sub; i--) {
                data[i] = data[i - 1];
            }
            data[sub] = val;
        } else {
            //TODO 扩容  size * 2
            size = size * 2;

        }

    }

    /**
     * 删除元素
     * @param sub 下标
     */
    public void remove(int sub){
        //从下标位置前移一位
        for (int i = sub; i < size; i++) {
            if (i != size - 1) data[i] = data[i + 1];
            else {
                data[i] = 0;    //初始值
            }
        }
    }

    /**
     * 更新对应下标元素
     * @param sub 下标
     * @param val 值
     * @return
     */
    public int update(int sub, int val){
        if (sub >= size){
            throw new IndexOutOfBoundsException();
        }

        return  data[sub] = val;
    }

    public static void main(String[] args) {
        Array arr = new Array(5);
        arr.add(0, 1);
        arr.add(1, 2);
        arr.add(2, 3);
        arr.add(3, 4);
        arr.add(4, 5);
        arr.remove(3);
        arr.update(4, 99);
        arr.update(10, 99);
        arr.print();

    }



}
