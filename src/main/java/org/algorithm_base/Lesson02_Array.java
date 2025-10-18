package org.algorithm_base;


/**
 * @author zengwang
 * @create 2024-06-29 10:21
 * @desc:
 */
public class Lesson02_Array {
    public static void main(String[] args) {
//        Lesson02_Array test = new Lesson02_Array();
//        System.out.println(test.rotate("abcdefg", 13));
//        System.out.println(test.rotate("abcdefg", 3));
//
//        int[] a = {1,2,3};
//        a = new int[]{2, 2, 3, 6, 6, 7, 7, 9,9,9};
//        int k = test.removeDuplicatesV2(a);
//        System.out.println("k is " + k);
//        for (int i = 0; i <= a.length - 1; i++) {
//            System.out.println(a[i]);
//        }
        MyArray ma = new MyArray();
        System.out.println(ma.insert(1, 1)); // false
        System.out.println(ma.insert(0, 1)); // true
        System.out.println(ma.insert(2, 1)); // true
        System.out.println(ma.insert(1, 1)); // true
        System.out.println(ma.insert(2, 1)); // true
        System.out.println(ma.insert(3, 1)); // true
        // 发生扩容
        System.out.println(ma.insert(4, 1)); // true
//        printArray(ma.arr);
        System.out.println(ma.deleteElem(3)); // true


    }
    public static void printArray(int[] array) {
        System.out.println("print array here");
        int len = array.length;
        for (int i = 0; i < len; i++) {
            System.out.println(array[i]);
        }
        System.out.println("print array end");
    }
    // 习题: 右移字符串
    public String rotate(String s, int k) {
        /*
        1. 长度为n的字符串，右移n位等于自身
        2. 右移多少位，就是把右边这部分的字符串截取后，整体挪到左边开头
        */
        // Shit!!
        int len = s.length();
        int moveSteps = k % len;

        char[] chars = s.toCharArray();
        // 就像吃羊肉串一样，每次从末尾取一个元素放到开头
        for(int i = 0; i < moveSteps; i++) {
            char tmp = chars[len - 1];
            for(int j = len - 1; j > 0; j--) {
                chars[j] = chars[j - 1];
            }

            chars[0] = tmp;
        }

        return new String(chars);
    }

    // 习题：原地删除有序数组的重复元素，返回不重复部分的长度
    public int removeDuplicates(int[] nums) {
        /**
         * 思路：既然要求原地处理，那么利用左移的原理，指针从前向后滑动，遇到重复元素就左移
         * 指针i从0开始，while true:
         * 1.判断相邻的两个元素，如果重复则左移进行抵消, 且移动终止于到最大元素的位置即可
         * 2.如果不重复，则i+=1
         * 3.如果最后a[i] 已经是升序数组的最大元素了，则退出循环
         */
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return 1;

        int i = 0;
        while (true) {
            if (nums[i] == nums[len-1]) break;
            else if (nums[i] == nums[i+1]) {
                // 每轮的左移优化: 移动终止于最大元素即可，没必要每次都移动整个数组
                for (int j = i; j < len - 1; j++) {
                    nums[j] = nums[j+1];
                    if (nums[j] == nums[len-1]) break;
                }
            } else if (nums[i] != nums[i+1]) i++;
        }
        return i + 1; // 要求返回长度
    }

    public int removeDuplicatesV2(int[] nums) {
        /**
         * 思路：双指针法
         * i、k都从0开始，k不断向后滑动，a[i] 代表当前的不重复元素段的末尾元素，
         * 如果其已经是最大元素了，那么结束
         *
         * 1.if a[i] == a[k]: 什么都不做，k 继续滑动
         * 2.if a[i] != a[k]: a[i+1] = a[k], 即保证i这个游标的路径，是我们维护的数组不重复元素
         *   当a[i+1]被覆盖后，更新i
         *
         * V1的缺点：V1针对每段重复的元素，都要做一次左移，
         * 其为了抵消重复元素，一段内做多轮左移，轮数等于该段内的重复数据个数
         */
        int len = nums.length;
        int i = 0;
        for (int k = 0; k < len && nums[i] != nums[len-1]; k++) {
            if (nums[i] != nums[k]) {
                nums[i+1] = nums[k];
                i += 1;
            }
        }

        return i + 1; // 要求返回长度
    }
}

/**
 * 实现一个动态可扩容的数组，数组中存储32位整型数，数组的初始容量为4，每次扩容为原来的2倍。
 * 数组支持插入、删除、按照下标查找数据这3个操作。3个操作的具体定义如下：
 *
 * 1.在 index 下标处插入元素 value，如果 index 的值超过当前数组中元素个数（即大于len），返回 false，
 *  否则返回 true（也就是说，数组的元素都紧邻靠前存储，不能空着前面位置不存数据，将数据存在后面的位置，导致数组特性【元素紧邻存放】被破坏）。
 * 2.删除下标为 index 的元素，如果 index 值超出数组下标范围 [0, len)，返回-1，否则，返回被删除元素值。
 * 3.按照下标 index 查找元素，如果下标 index 超出数组下标范围 [0, len)，返回-1，否则，返回被查找元素值。
 * 注：len 表示数组中的元素个数。
 */
class MyArray {
    // 始终保持数组在插入数据前是容量足够的
    private int[] arr;
    // 代表当前数组中元素个数, 从0开始，插入后就+1
    // 因为必须元素紧紧相邻插入，它也代表下一次最大可插入的index
    private int n;
    private int capacity; // 数组的容量

    public MyArray() {
        this.capacity = 4;
        this.arr = new int[this.capacity];
        this.n = 0;
    }

    public boolean insert(int index, int value) {
        if (index > this.n || index < 0) {
            return false;
        }
        // 1.index <= n的情况
        // 1.1 此时index可以等于n，直接插就行
        if (index == n) {
            this.arr[index] = value;
        } else {
            // 1.2 如果index < n, 说明index的位置已被占用过，需要先腾出位置
            for (int i = n; i > index; --i) {
                this.arr[i] = this.arr[i-1];
            }
            this.arr[index] = value;
        }
        // 其实1.1\1.2的case都可以统一用1.2来表示

        this.n += 1;
        // 2.完成插入后，如果数组当前的元素个数等于容量，则需要扩容
        // 扩容逻辑
        if (this.n == this.capacity) {
            this.capacity = 2 * this.capacity;
            int[] tmp = new int[this.capacity];

            for (int i = 0; i < this.n; ++i) {
                tmp[i] = this.arr[i];
            }
           this.arr = tmp;
        }

        return true;
    }

    public int deleteElem(int index) {
        if (index >= this.n || index < 0) {
            return -1;
        }
        int tmp = this.arr[index];
        // 删除元素，将后面的元素往前挪动
        for (int i = index; i < this.capacity - 1; ++i) {
            this.arr[i] = this.arr[i+1];
        }
        this.n -= 1;
        return tmp;
    }

    public int get(int index) {
        if (index >= this.n || index < 0) {
            return -1;
        }
        return this.arr[index];
    }
}

