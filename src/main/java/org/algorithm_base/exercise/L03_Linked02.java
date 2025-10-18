package org.algorithm_base.exercise;

public class L03_Linked02 {
    public ListNode insert(ListNode head, int value) {
        /**
         * 引入虚拟头节点
         * 思路：从头开始找到第一次值大于value的节点，此时获取pre节点，进行插入
         * 边界case：链表为空、链表头插入、尾插入、元素已存在
         */
        ListNode newNode = new ListNode(value);
        if (head == null) {
            return newNode;
        }

        ListNode vNode = new ListNode(Integer.MIN_VALUE, head);
        ListNode prev = vNode;
        ListNode p = head;
        while (p != null && value > p.val) {
            prev = p;
            p = p.next;
        }

        prev.next = newNode;
        newNode.next = p;

        return vNode.next;
    }
}
