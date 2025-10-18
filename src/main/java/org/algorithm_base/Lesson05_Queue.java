package org.algorithm_base;

import org.algorithm_base.exercise.ListNode;

/**
 * 基于链表实现 队列 这种数据结构，支持队列的以下几个基本操作：
 * 入队 enqueue(value)
 * 出队 dequeue() ，如果队列为空，返回-1。
 * 判空 isEmpty()
 */

public class Lesson05_Queue {
    // 链式队列，从链表头进行出队，从链表尾部入队
    private ListNode head;
    private ListNode tail;

    public void enqueue(int value) {
        ListNode newNode = new ListNode(value);
        // 队列为空的case
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public int dequeue() {
        // 队列为空的case
        if (head == null) {
            return -1;
        }
        int value = head.val;
        // 队列只有一个元素，即将为空，本地出队操作后，head指向了空，tail也得指向空，回到初始状态
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    public boolean isEmpty() {
        return head == null;
    }
}