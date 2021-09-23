package com.xyt.leecode.sort;

import java.util.Arrays;

/**
 * @Author: xyt
 * @Description: 堆排序
 * @Date: 2021/9/3 17:46
 */
public class HeapSort {

    /**
     * 堆化 大顶堆的构建
     * @param nums  数据
     * @param start 开始位置
     * @param end   堆化结束位置 已经排序好的必须要再去堆化。所以会有end位置
     */
    private static void maxHeap(int[] nums, int start, int end){

        int parent = start; //从最后一个父节点开始遍历
        int son = parent * 2 + 1;   //下标从0开始（左孩子） 完全二叉树性质， 节点位置为 2 * i + 1

        while (son < end){  //当子节点 超出了end （已经排过序的节点）则不需要在堆化
            int data = son;     //孩子节点， son + 1 为 右孩子节点 data即表示左右孩子节点最大的节点
            if (son + 1 < end && nums[son] < nums[son + 1]){
                //右孩子 也不能超过end， 如果右孩子比左孩子要大
                //则交换右孩子
                data = son + 1;
            }
            if (nums[parent] > nums[data]) return; //父节点比孩子要大 不需要再次交换
            else { //父节点与大的孩子节点交换
                swap(nums, parent, data);
                parent = data;      //循环
                son = parent * 2 + 1;
            }
        }
    }

    /**
     * 构建小顶堆
     * @param nums
     * @param start
     * @param end
     */
    private static void minHeap(int[] nums, int start, int end){
        int parent = start;
        int son = parent * 2 + 1;

        while (son < end){
            int data = son;
            if (son + 1 < end && nums[son + 1] < nums[son]){
                //小则交换
                data = son + 1;
            }
            if (nums[parent] < nums[data]) return; //父节点比孩子节点小不需要交换
            else {
                swap(nums, parent, data);
                parent = data;
                son = parent * 2 + 1;
            }
        }
    }

    /**
     * 堆排序
     * @param isOrder true 大顶堆 false 小顶堆
     * @param nums
     */
    public static void heapSort(int[] nums, boolean isOrder){

        int len = nums.length;
        //从最后一个有叶子节点的结点开始遍历  也就是位置： len / 2 - 1 （完全二叉树）
        //将每一个子树堆化。
        for (int i = len / 2 - 1; i >= 0 ; i--) { //时间复杂度  O(nlog n)
            if (isOrder){
                maxHeap(nums, i, len);
            } else {
                minHeap(nums, i, len);
            }
        }
        //将第一个与最后一个替换, 已经交换过的不需要再次交换 i = len - 1；
        // 从堆顶开始堆化。 最后层次遍历获得排序后的数组。
        //此处也可以套用删除的逻辑；
        //删除 应将堆顶与最后一个节点交换。 然后从堆顶堆化
        //
        for (int i = len - 1; i > 0 ; i--) { //时间复杂度  O(nlog n)1
            swap(nums, 0, i);
            if (isOrder){
                maxHeap(nums, 0, i);    //i 因为len ~ i 已经排好序了。
            } else {
                minHeap(nums, 0, i);
            }
        }
    }

    /**
     * 数组交换
     * @param arr
     * @param left
     * @param right
     */
    private static void swap(int[] arr, int left, int right){
        arr[left] = arr[left] ^ arr[right];
        arr[right] = arr[left] ^ arr[right];
        arr[left] = arr[left] ^ arr[right];
    }
}

class HeapSortTest{

    public static void main(String[] args) {
        int[] arr = {5, 9, 3, 7, 1, 6, 2};
        HeapSort.heapSort(arr, false);
        System.out.println(Arrays.toString(arr));
    }
}
