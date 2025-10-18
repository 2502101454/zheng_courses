package org.algorithm_base;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class Lesson03_Linked {
    private Node head = new Node();
    private Node tail = head;

    public void insertAtTail3(int value) {
        Node node = new Node(value, null);
        tail.next = node;
        tail = node;
    }
    public void insertSpecifiedNode(Node p, int value) {
        if (p == null) return;
        Node node = new Node(value, p.next);
        p.next = node;
    }
    public void deleteNextNode(Node p) {
        if (p == null || p.next == null) return;
        p.next = p.next.next;
    }

    public Node deleteThisNode(Node head, Node p) {
        /** 思路：去找前驱节点
         * 我们先实现正常情况，然后考虑一些边界case
         * 1.链表为空
         * 2.链表只有一个节点
         * 3.找到要删除的节点了，但是删除节点为头节点
         */

        if (p == null) return head;

        // 前驱节点初始化为null
        Node pre = null;
        Node q = head;
        while (q != null) {
            if (q == p) {
                break;
            }
            // 能走这，就是本轮没找到
            pre = q;
            q = q.next;
        }
        // 遍历完链表之后，还没找到删除的节点，所以不操作了(含链表为空的case)
        if (q == null) return head;
        // q不是null的时候，一定是中途break出来了，即找到了删除的节点
        if (pre == null) {
            // 删除的是头节点
            head = head.next;
        } else { //删除的不是头节点
            pre.next = q.next;
        }

        return head;
    }
    public Node deleteThisNode2(Node head, Node p) {
        /** 优化思路：使用虚拟头节点，将对删除头节点的操作进行统一兼容
         * 引入虚拟头节点后：
         * 1.p为null，或者找不到p时，逻辑是没变的，都是不操作
         * 2.当找到p的时候，q和前驱的pre 节点一定存在，所以可以统一处理（因为pre初始化就不为null了）
         *
         * 先调通正常情况下，再考虑head 为null、删除头节点等情况
         */

        if (p == null) return head;

        Node vNode = new Node(Integer.MIN_VALUE, head);

        // 前驱节点初始化为vNode
        Node pre = vNode;
        Node q = vNode.next;
        while (q != null) {
            if (q == p) {
                break;
            }
            // 能走这，就是本轮没找到
            pre = q;
            q = q.next;
        }
        // 遍历完链表之后，还没找到删除的节点，所以不操作了(含链表为空的case)
        if (q == null) return head;
        // q不是null的时候，一定是中途break出来了，即找到了删除的节点
        pre.next = q.next;

        return vNode.next;
    }


    public static void main(String[] args) {

    }
}

@AllArgsConstructor
@NoArgsConstructor
class Node {
	int data;
	Node next;
}
