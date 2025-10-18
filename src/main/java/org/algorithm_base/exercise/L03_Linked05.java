package org.algorithm_base.exercise;

/**
 * n个人围成一圈，编号分别为 1 到 n ，编号为 1 的人从 1 开始报数，数到 m 的人出圈，
 * 再由下一个人重新从 1 开始报数，数到 m 的人再出圈，依次类推，直到所有的人都出圈，请输出依次出圈人的编号
 * 输入：n=10, m=3
 * 输出：[3,6,9,2,7,1,8,5,10,4]
 */
public class L03_Linked05 {
    public int[] joseph(int n, int m) throws InterruptedException {
        /**
         * 思路：构造单向循环链表，然后不断的进行m次遍历，输出m的value
         * 注意环形链表的起始p指针是head，prev指针则是tail节点
         * 正常case: 同上
         * 边界case:
         *  1.1<=n, m<=100，所以不可能是空链表,
         *  2.注意删除到最后只剩下3个节点的环，变成2个节点，再变成1个节点了，以及如何处理null case？
         */

        // 使用虚拟头节点统一业务链表的创建逻辑，注意业务链表尾部，不会挂在虚拟头节点上！
        ListNode vHead = new ListNode(0);
        ListNode tail = vHead;
        // 构建单循环链表
        for (int i = 1; i <= n; i++) {
            ListNode newNode = new ListNode(i);
            tail.next = newNode;
            tail = newNode;
        }
        tail.next = vHead.next;

        // 出圈, 涉及到删除节点，所以需要prev
        int count = 0;
        int[] res = new int[n];
        ListNode prev = tail; // prev节点是尾节点
        ListNode p = vHead.next; // 从业务节点开始遍历
        while (p != null) {
            for (int j = 1; j < m; j++) {
                prev = p;
                p = p.next;
            }
            res[count] = p.val;
            // 最后一个节点时，删除逻辑进入死循环，通过人头数退出
            if (count == n -1) break;
            count += 1;
            prev.next = p.next;
            p = p.next;
        }
        return res;
    }

    public static void main(String[] args) throws InterruptedException {
        L03_Linked05 test = new L03_Linked05();
        for (int x: test.joseph(5, 3)) {
            System.out.println(x);
        }
    }
}
