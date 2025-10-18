package org.algorithm_base.exercise;
// 实现链式栈
public class L04_Stack02 {
    // 链式栈就不行数组栈(顺序栈)那样具有固定长度了
    private ListNode head;
    public void push(int value) {
        // 用链表头作为栈被操作的那一端————栈顶
        ListNode newNode = new ListNode(value, head);
        head = newNode;
    }

    public int pop() {
        if (head == null) {
            return -1;
        } else {
            int val = head.val;
            head = head.next;
            return val;
        }
    }

    public int peek() {
        return head != null ? head.val : -1;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
