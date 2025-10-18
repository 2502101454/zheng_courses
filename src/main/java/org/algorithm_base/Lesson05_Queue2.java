package org.algorithm_base;

/**
 * 基于数组实现循环队列，队列支持以下几个基本操作：
 * 入队 enqueue(value) , 如果队列满了，则返回false，否则返回true。
 * 出队 dequeue() ，如果队列为空，返回-1。
 * 判空 isEmpty()
 * 循环队列的大小通过构造函数的参数 queueSize 来传递。
 */
public class Lesson05_Queue2 {
    private int[] arr;
    private int head; // 当前队列的头指针
    private int tail; // 当前队列的尾指针

    // 只要实现头出队，尾入队的逻辑就行，谁关心你的指针初始咋设置，-1的解法也是很常见的
    public Lesson05_Queue2(int queueSize) {
        arr = new int[queueSize];
        // 当前队列为空，所以初始指针都不存在；队列有一个元素的时候，head、tail都指向它
        head = -1;
        tail = -1;
    }

    public boolean enqueue(int item) {
        // 队列已满，插入失败: 如果tail下次再运动就到head了，那肯定满了
        int tmp_next = (tail + 1) % arr.length;
        if (tmp_next == head) return false;

        arr[tmp_next] = item;
        // 如果当前队列为空, head、tail都应该指向同一个元素
        if (head == -1) head = 0;
        tail = tmp_next;
        return true;
    }

    public int dequeue() {
        // 队列为空的case: 出队的时候一定要每次判断当前是否已经空了，及时重置
        if (head == -1) return -1;

        // 队列不为空，出队头节点
        int data = arr[head];
        head = (head + 1) % arr.length;
        // 如果head 越过了tail了，当前tail的下一格是head，就说明空了，重置head、tail
        int tmp_next = (tail + 1) % arr.length;
        if (tmp_next == head) {
            head = -1;
            tail = -1;
        }
        return data;
    }

    public boolean isEmpty() {
        return head == -1;
    }
}