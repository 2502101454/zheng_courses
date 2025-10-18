package org.algorithm_base.exercise;
public class L03_Linked01 {
    public ListNode insertAtHead(ListNode head, int value) {
        // 头插，在链表的头部插入一个节点，并返回新的头节点。
        ListNode newNode = new ListNode(value, head);
        return newNode;
    }

    public ListNode insertAtTail(ListNode head, int value) {
        ListNode newNode = new ListNode(value);
        if (head == null) {
            head = newNode;
        } else {
            ListNode p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = newNode;
        }
        return head;
    }

    public int size(ListNode head) {
        int l = 0;
        ListNode p = head;
        while (p != null) {
            l++;
            p = p.next;
        }
        return l;
    }

    //查找前驱，查找并返回给定节点 enode 的前驱节点
    public ListNode prev(ListNode head, ListNode enode) {
        // 先考虑正常情况，然后考虑边界case
        /**
         * 不太适用于虚拟头节点，因为pre被初始化为虚拟头节点，如果返回pre就不对了
         * 1.正常情况，链表长，目标节点在链表中间
         * 2.边界情况：
         *  2.1 找不到的情况-为空：链表为空、目标节点为空
         *  2.2 找不到的情况-不为空：链表不为空，但是目标节点不在链表中
         *  2.3 找到的情况：目标节点为头节点、尾节点
         */

        if (head == null || enode == null) {
            return null;
        }

        ListNode pre = null;
        ListNode p = head;
        while (p != enode) {
            pre = p;
            p = p.next;
            if (p == null) {
                return null;
            }
        }
        return pre;
    }

    public ListNode delete(ListNode head, ListNode enode) {
        /**
         * 删除指定的节点 enode，并返回头节点。适用于虚拟头节点，因为是始终要返回head
         * 正常：删除的节点在链表中间
         * 边界：
         *  1.找不到删除节点：链表为空、目标节点为空
         *  2.找不到删除节点：链表不为空、目标节点不为空
         *  3.找得到删除节点：目标节点为头节点、尾节点
         *  4.链表只有一个节点
         */
        if (enode == null || head == null) {
            return head;
        }
        // 进行虚拟头节点、pre的初始化
        ListNode vNode = new ListNode();
        vNode.next = head;
        ListNode pre = vNode;
        ListNode p = vNode.next;

        while (p != enode) {
            pre = p;
            p = p.next;
            if (p == null) {
                return vNode.next;
            }
        }
        pre.next = p.next;
        return vNode.next;
    }

    public static void main(String[] args) {
        L03_Linked01 linked01 = new L03_Linked01();
    }
}
