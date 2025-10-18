package org.algorithm_base.exercise;

/**
 * @author zengwang
 * @create 2025-02-20 17:35
 * @desc:
 */

import java.util.Arrays;

/**
 只用一个数组来实现三个 循环队列，支持操作如下：

 入队 enqueue(queueID, value) ，如果队列满了，则返回false，否则返回true。
 出队 dequeue(queueID) ，如果队列为空，则返回-1，否则返回出队元素值。
 查看队列是否为空 isEmpty(queueID)。
 在上述操作中，queueID表示队列编号（取值为0、1、2），value表示入队的元素值。构造函数会传入一个queueSize参数，代表每个队列的大小。
 */
public class L05_Queue03 {
    /**
     * 思路：核心最终要的就是两个变量，偏移 和 size
     * 1.每个队列（数组的每段）大小都是一样的。偏移正好等于size
     * 2.有偏移后，对于任意一段，站在段内视角看index集的相对位置，index % size就抵消了偏移，得到段内的相对index[0, size)
     * 3.给一段内的相对index 加上偏移，即 index % size + n*Size，就得到该段在整个数组的绝对index
     *
     * 使用三个数组head = []，tail， count；  (仅仅使用head 和 tail且两者不错位的情况下判断队列为空，太费劲了，还是别折腾了)
     */
    public int[] arr;
    public int size; // 队列的大小
    public int[] head; // 当前队列的头指针
    public int[] tail; // 当前队列的尾指针
    public int[] count; // 当前队列中的元素个数

    public L05_Queue03(int queueSize) {
        arr = new int[queueSize * 3];
        // 初始化三组队列的状态变量
        head = new int[]{-1, -1, -1};
        tail = new int[]{-1, -1, -1};
        count = new int[]{0, 0 ,0};
        size =queueSize;
    }

    public boolean enqueue(int queueID, int value) {
        /**
         * 入队，队尾部插入
         * 1.确定tail 下标
         * 2.进行插入
         */

        // 队列满了
        if (count[queueID] == size) return false;

        // 正常情况下
        tail[queueID] = (tail[queueID] + 1) % size + queueID * size;
        arr[tail[queueID]] = value;
        count[queueID] += 1;
        // 空队列第一次插入值，head、tail指向同一处
        if (head[queueID] == -1) head[queueID] = tail[queueID];
        return true;
    }

    public int dequeue(int queueID) {
        // 队列空了
        if (count[queueID] == 0) return -1;

        int value = arr[head[queueID]];
        // 更新head指针：head 和tail的初始状态以及运动轨迹一致，所以一个算法
        head[queueID] = (head[queueID] + 1) % size + queueID * size;
        count[queueID] -= 1;

        System.out.println("arr = " + Arrays.toString(arr));
        // 实际上不用：如果本次出队后，队列变空了，这里重置head、tail指针为初始状态
        // 为空时 head会出现在tail后面，再插入的时候，head和tail又对齐了
        return value;
    }

    public boolean isEmpty(int queueID) {
        return count[queueID] == 0;
    }

    public static void main(String[] args) {
        L05_Queue03 queue = new L05_Queue03(4);
        System.out.println(queue.enqueue(1, 1));
        queue.enqueue(1, 2);
        queue.enqueue(1, 3);
        System.out.println(queue.enqueue(1, 4));
        System.out.println(queue.enqueue(1, 5));
        System.out.println(queue.dequeue(1) + ",tail = " + queue.tail[1] + ",head = " + queue.head[1] + ",count = " + queue.count[1] );
        System.out.println(queue.dequeue(1) + ",tail = " + queue.tail[1] + ",head = " + queue.head[1] + ",count = " + queue.count[1] );
        System.out.println(queue.dequeue(1) + ",tail = " + queue.tail[1] + ",head = " + queue.head[1] + ",count = " + queue.count[1] );
        System.out.println(queue.dequeue(1) + ",tail = " + queue.tail[1] + ",head = " + queue.head[1] + ",count = " + queue.count[1] );
        System.out.println(queue.dequeue(1) + ",tail = " + queue.tail[1] + ",head = " + queue.head[1] + ",count = " + queue.count[1] );
        System.out.println("--------");
        System.out.println(queue.enqueue(1, 5));
        System.out.println(queue.dequeue(1) + ",tail = " + queue.tail[1] + ",head = " + queue.head[1] + ",count = " + queue.count[1] );
    }
}