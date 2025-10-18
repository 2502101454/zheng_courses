package org.algorithm_base.exercise;

public class L03_Linked03 {
    public ListNode removeElements(ListNode head, int val) {
        /**
         * 给定一个链表的 head和一个整数val ，请删除链表中所有满足 Node.val == val 的节点，并返回新的头节点。
         * 思路：使用虚拟头节点技巧，遍历一把链表，记住前驱prev 和当前节点cur，如果当前节点cur.val == val，则删除当前节点cur
         * 正常case：链表不为空，存在多个重复元素；
         * 边界case：
         * 1.删除的元素是连续的
         * 2.删除的是头节点
         * 3.链表为空
         */
        ListNode vNode = new ListNode(0, head);
        ListNode prev = vNode;
        ListNode cur = vNode.next;
        while (cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
                // 删除完节点之后，prev并不能立马移动，因为还无法确定下一轮的cur是否依然要被删除
            } else {
                prev = cur;
            }
            // 无论当前节点cur是否要被删除，遍历链表，cur都得继续向后走，才能判断下一个节点
            cur = cur.next;
        }
        return vNode.next;
    }
}
