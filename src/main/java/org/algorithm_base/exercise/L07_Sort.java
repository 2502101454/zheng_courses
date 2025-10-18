package org.algorithm_base.exercise;

import java.util.Arrays;

public class L07_Sort {
    public static void main(String[] args) {
//        int [] arr = {3, 2, 5, 1, 6};
        int [] arr = {3, 4};
//        int [] arr = {1, 2, 3, 7, 6};
        L07_Sort l07Sort = new L07_Sort();
//        l07Sort.bubbleSort(arr, 5);
//        System.out.println(Arrays.toString(arr));

        l07Sort.insertSort(arr, 2);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序的实现：正序
     * 1.实际n-1轮可以停止
     * 2.这一轮没有任何交换发生，可以提前停止
     * 3.每一轮从左到右遍历，右边的区间逐渐缩短，因为右边末尾是上一轮已经调整好的数
     * @param arr
     * @param n
     */
    public void bubbleSort(int [] arr, int n) {
        if (n <= 1) return;
        for (int i = 0; i < n - 1; i++) { // n-1轮, i从0开始，i取到n-1 就是n轮
            boolean swap = false; // 有交换则标记为true
            for(int j = 0; j < n - 1 - i; j++) // 例i =0时，j<n-1, j+1 才能达到n-i
                if (arr[j] > arr[j+1]) {
                   int tmp = arr[j];
                   arr[j] = arr[j+1];
                   arr[j+1] = tmp;
                   swap = true;
                }
            if (!swap) break;
        }
    }

    /**
     * 正序
     *  1.每轮都有得插: 从未排序区间依次取数据（从前先后），我理解j没加1之前，两个区间划分不更新
     *     1.1 插入到未排序区间，就是自己覆盖自己
     *     1.2 插入到已排序区间，就是数组段搬迁
     *  2.巧妙之处：两个逻辑分支的代码是兼容的
     * @param arr
     * @param n
     */
    public void insertSort(int [] arr, int n) {
        if (n <= 1) return;
        // a. i来维护已排序区间下标，j维护未排序区间下标;
        // 初始时i只有一个元素，j从i+1开始

        // b. 每轮取j中一个元素，j就是区间划分的界碑
        for (int j=1; j<n; j++) {
            int i = j - 1; // 业务意义：这里的i不是一个下标，而是代表一个长度为i的区间
            // 在已排序区间从后向前寻找插入位置
            while (i >= 0 && arr[j] < arr[i]) {
                i--;
            }
            // c.找到插入位置后，进行空间搬迁，腾位置, k维护数组搬迁段的下标
            // 找到arr[i]代表的区间中，第一次arr[j]>=arr[i]的位置了，腾空间，插入到arr[i+1]
            int tmp = arr[j];
            for (int k=j; k>i+1; k--) {
                arr[k] = arr[k-1];
            }
            arr[i+1] = tmp;
        }
    }
}
