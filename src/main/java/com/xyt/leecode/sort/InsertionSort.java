package com.xyt.leecode.sort;

/**
 * @Author: xyt
 * @Description:    插入排序
 * @Date: 2021/8/20 12:44
 */
public class InsertionSort {

    /**
     * 插入排序思路：
     *  1. 将数组分成已排序段和未排序段；
     *  2. 从为排序段取出元素去已排序段比较，插入合适的位置。
     *  3. 重复操作直到未排序段全部排序
     *
     *  注意： 取数据时应从尾开始取数，
     */

    /**
     * 插入排序实现
     * @param nums
     */
    public static void sort(int[] nums){
        if (nums.length == 0){
            throw new NullPointerException();
        }

        //通过双for循环 时间复杂度O(n^2); 最好的时间复杂度为O(n)
        // 内部循环每次只循环一次 即每次都走break
        //外层for循环从1开始， 因为第一次循环 第一个数字 即为已排序段
        //循环 未排序段
        for (int i = 1; i < nums.length; i++) {
            int data = nums[i];     //  待排序值 （必须有） 临时存储值
            int j = i - 1;  //二次循环指针，从尾部开始循环
            for( ; j >= 0; j--){//从尾向头遍历 循环排序段
                if (nums[j] > data) //若取出的数 大于前者，数组向后移动
                    nums[j + 1] = nums[j];  //将前面已排序数组按次向后移动一位
                else
                    //break执行的次数越多， 时间复杂度越快。
                    // 优化排序算法，从这里着手。
                    break;  //若取出的小 小于前者 调出循环赋值，不需要再去前面比遍历
            }
            nums[j + 1] = data; //赋值 插入当前循环j的后一个位置，同时也是这个位置空出来的。
        }
    }

    public static void sort_print(int[] nums){
        if (nums.length == 0){
            throw new NullPointerException();
        }

        //通过双for循环
        //外层for循环从1开始， 因为第一次循环 第一个数字 即为已排序段
        //循环 未排序段
        for (int i = 1; i < nums.length; i++) {
            int data = nums[i];     //  待排序值 （必须有） 临时存储值
            int j = i - 1;  //二次循环指针，从尾部开始循环
            for( ; j >= 0; j--){//从尾向头遍历 循环排序段
                if (nums[j] > data){ //若取出的数 大于前者，数组向后移动
                    nums[j + 1] = nums[j];  //将前面已排序数组按次向后移动一位
                } else
                    break;  //若取出的小 小于前者 调出循环赋值，不需要再去前面比遍历
            }
            nums[j + 1] = data; //赋值
            System.out.print("第" + i + "次排序结果为：");
            for (int k = 0; k < nums.length; k++) {
                System.out.print(nums[k] + ",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 5, 1, 8, 6};
        sort(arr);
    }
}
