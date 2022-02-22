package com.xyt.leecode;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Author: xyt
 * @Date: 2022/1/24 9:46
 * @version: 1.0.0
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        Random random = new Random();

        for(int i = 0; i < 16; i++) {
            set.add(random.nextInt(20));
        }
        log.info("set : -> {}", set.toString());

        int[] nums = {2, 7, 1, 5, 4, 3};
        heapSort(nums);
        int i = binarySearch(nums, 2);
        quickSort(nums, 0, nums.length - 1);
        chooseSort(nums);
        buddingSort(nums);
        insertSort(nums);
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(nums);

    }


    /**
     * 归并
     * @param nums
     * @param left
     * @param right
     */
    public static void mergeSort(int[] nums, int left, int right){
        if (left < right){
            int mid = (left + right) / 2;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);
            merge(nums, left, mid, right);
        }
    }

    public static void merge(int[] nums, int left, int mid, int right){
        // 临时数组
        int[] temp = new int[nums.length];
        int r = mid + 1; int l = left;
        int index = left;
        while (l <= mid && r <= right){
            if (nums[l] < nums[r]){
                temp[index] = nums[l];
                l ++;
            } else {
                temp[index] = nums[r];
                r ++;
            }
            index ++;
        }

        while (l <= mid){
            temp[index ++] = nums[l ++];
        }
        while (r <= right){
            temp[index ++] = nums[r ++];
        }

        for (int i = left; i <= right; i++) {
            nums[i] = temp[i];
        }
    }

    /**
     * 插入排序
     * @param nums
     */
    public static void insertSort(int[] nums){
        if (nums.length == 0){
            return;
        }

        for (int i = 1; i < nums.length; i++) {
            int data = nums[i];
            int j = i - 1;
            // 从尾部开始遍历
            for (; j >= 0 ; j--) {
                if (nums[j] > data){
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = data;
        }
    }

    /**
     * 冒泡排序
     * @param nums
     */
    public static void buddingSort(int[] nums){
        if (nums.length == 0){
            return;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j + 1] < nums[j]){
                    swap(nums, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) break;;
        }
    }

    private static void swap(int[] nums, int left, int right){
        nums[left] = nums[left] ^ nums[right];
        nums[right] = nums[left] ^ nums[right];
        nums[left] = nums[left] ^ nums[right];
    }


    /**
     * 选择排序
     * @param nums
     */
    public static void chooseSort(int[] nums){
        if (nums.length == 0){
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int curr = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]){
                    curr = j;
                    swap(nums, i, curr);
                }
            }
        }
    }

    /**
     * 快排
     * @param nums
     */
    public static void quickSort(int[] nums, int left, int right){
        if (left > right){
            return;
        }

        // 基准数
        int base = nums[left];
        int l = left, r = right;
        while (l < r){
            // 第一步 从末尾开始找 比基数小的值 ，交换
            while (l < r && base <= nums[r]){
                r --;
            }
            if (l < r){
                swap(nums, l, r);
                l ++;
            }
            // 第二步 从开头开始找比基数大的交换
            while (l < r && base >= nums[l]){
                l ++;
            }
            if (l < r){
                swap(nums, l, r);
                r --;
            }
        }
        // 尾递归
        if (left < l){
            quickSort(nums, left, l - 1);
        }
        if (l < right){
            quickSort(nums, l + 1, right);
        }
    }

    /**
     * 堆排序 - 大顶堆 堆化构建
     * @param nums
     * @param s
     * @param e
     */
    private static void maxHeap(int[] nums, int s, int e){
        int parent = s;
        // 下标从0开始 ，根据二叉树性质，子节点位置为 parent * 2 + 1
        int son = (parent << 1) + 1;
        while (son < e){
            int data = son;
            if (son + 1 < e && nums[son] < nums[son + 1]){
                data = son + 1;
            }
            if (nums[parent] > nums[data]) return;
            else {
                swap(nums, parent, data);
                parent = data;
                son = (parent << 1) + 1;
            }
        }
    }

    /**
     * 堆排序
     * @param nums
     */
    public static void heapSort(int[] nums){
        int len = nums.length;
        if (len == 0) return;

        for (int i = (len >>> 1) - 1; i >= 0; i --) {
            maxHeap(nums, i, len);
        }

        for (int i = len - 1; i > 0 ; i --) {
            swap(nums, 0, i);
            maxHeap(nums, 0, i);
        }
    }


    /**
     * 二分查找
     * @param nums
     * @param target
     */
    public static int binarySearch(int[] nums, int target){
        int l = 0, h = nums.length - 1;
        while (l <= h){
            int mid = (h - l) / 2 + l ;
            if (nums[mid] < target){
                l = mid + 1;
            } else if (nums[mid] > target){
                h = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
