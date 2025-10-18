package org.algorithm_base.exercise;

/**
 * @author zengwang
 * @create 2025-01-31 17:24
 * @desc:
 */

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val;}
    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
