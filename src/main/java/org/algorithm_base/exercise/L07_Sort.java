package org.algorithm_base.exercise;

import java.util.Arrays;

public class L07_Sort {
    public static void main(String[] args) {
//        int [] arr = {6,3,4,2,5,1};
//        int [] arr = {1, 2, 3, 1, 6, 5};
//        int [] arr = {3, 4};
        int [] arr = {4};
//        int [] arr = {1, 2, 3, 7, 6};
        L07_Sort l07Sort = new L07_Sort();
//        l07Sort.bubbleSort(arr, 5);
//        System.out.println(Arrays.toString(arr));

//        l07Sort.insertSort(arr, arr.length);
//        l07Sort.selectionSort(arr, arr.length);
        l07Sort.mergeSort(arr, arr.length);
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

    /**
     * 版本1 没有get到精髓：找插入位置的过程中，是可以做数组移位的.
     * 把所有比a[j]大的元素都往后挪动，直到遇到比a[j]小的，此时插入的位置就出现了
     * @param arr
     * @param n
     */
    public void insertSort2(int [] arr, int n) {
        if (n <= 1) return;
        // a. i来维护已排序区间下标，j维护未排序区间下标;
        // 初始时i只有一个元素，j从i+1开始

        for (int j=1; j<n; j++) {
            int i = j - 1; // 业务意义：这里的i不是一个下标，而是代表一个长度为i的区间
            int tmp = arr[j];

            // 在已排序区间从后向前寻找插入位置
            for (; i >= 0; i--) {
                if (arr[i] > tmp) { // 把比tmp大的都往后移动，会被连续命中调用一段时间
                    arr[i+1] = arr[i];
                } else {
                    break;
                }
            }

            arr[i+1] = tmp;
        }
        // b. 每轮取j中一个元素，j就是区间划分的界碑
    }

    /** 以正序为例
     * 核心：不关注元素值，只关心下标为0的位置，要放最小元素，依次类推
     * 实现：
     *      1.将数组分为已排序区间和未排序区间，最开始没有已排序区间，全都是未排序区间
     *      2.每一轮：遍历整个未排序区间，选出最小值，然后扩充已排序区间(因此，每一轮是未排序区间-1.已排序区间+1)
     *      3.n个元素 经历n-1轮，n-1个元素都位置正确了，最后一个自然就正确了
     * @param arr
     * @param n
     */
    public void selectionSort(int [] arr, int n) {
        if (n <= 1) return;
        // 1. i代表轮数
        for (int i = 0; i < n-1; i++) { //推导 1~n-1，是n-1轮；0~n-1,是n轮；0~ < n-1，即为n-1轮；
            // 2.同时i的业务意义：起初i属于未排序区间的，但是本轮操作结束后，i就属于已排序区间了

            // 整个未排序区间内，找最小元素：取头元素，暂定为本轮最小元素，用index就可以访问，不用再起变量
            int minPosi = i;
            for (int j = i+1; j < n; j++) {
                if (arr[minPosi] > arr[j]) {
                    minPosi = j;
                }
            }

            // 本轮结束：选择到最小的元素了，做交换，i这回才变成已排序区间
            int tmp = arr[i];
            arr[i] = arr[minPosi];
            arr[minPosi] = tmp;
        }
    }

    /**
     * 1.识别可以上递归
     * 2.列出递推公式
     * 3.翻译代码
     *
     * @param arr
     * @param n
     */
    public void mergeSort(int [] arr, int n) {
        mergeSort(arr, 0, n-1); // 调用重载方法
    }

    /**
     *
     * @param arr
     * @param p 下标，包含该边界
     * @param q 下标，包含该边界
     */
    public void mergeSort(int [] arr, int p, int q) {
        // 最小问题，已有解
        if (p >= q) return;

        /*
         1.划分子问题, 求解子问题
         说明：以前老纠结r值到底能不能将数组完美划分两半？数组长度奇、偶时会有啥影响？
         ！！实际上r就是一个划分而已，均不均匀不重要，最后都会触达到子问题的！！
         */
        int r = p + (q-p)/2; // 防止int 精度溢出，改成偏移量的方式实现，本来是(q+p)/2
        mergeSort(arr, p, r);
        mergeSort(arr, r+1, q);

        // 2.利用上面子问题的解，组合得到原问题的解
        merge(arr, p, r, q);
    }

    // 合并两个有序数组，然后覆盖arr
    private void merge(int[] arr, int p, int r, int q) {
        int [] tmp = new int[q-p+1];

        // 双指针
        int i = p;
        int j = r + 1;
        int k = 0;
        while(i <= r && j <= q) {
            if (arr[i] <= arr[j]) {
                tmp[k] = arr[i];
                i += 1;
            } else {
                tmp[k] = arr[j];
                j += 1;
            }
            k += 1;
        }

        for (; i <= r; i++) {
            tmp[k] = arr[i];
            k += 1;
        }

        for (; j <= q; j++) {
            tmp[k] = arr[j];
            k += 1;
        }

        // 覆盖arr段的时候，注意起始位置
        for(k = 0; k <= tmp.length-1; k++) {
            arr[p+k] = tmp[k];
        }
    }

}
