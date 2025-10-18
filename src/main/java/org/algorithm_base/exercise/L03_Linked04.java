package org.algorithm_base.exercise;


/**
 * 基于单链表实现 LinkedList容器，容器支持以下操作：
 * 添加元素：在 LinkedList 尾部添加元素。
 * PS: 链表维护length 和 index，就和数组一样，index从0开始
 * 按照索引插入元素：在下标 index处插入元素，如果 index>n（n 表示当前容器中 元素个数），则返回false，否则返回true。
 * 按照索引删除元素：删除下标 index 处的元素，如果索引 index>=len ，则返回-1，否则返回要删除元素值。
 * 按照索引查找元素：查找下标 index 的元素，如果索引 index>=len ，则返回-1，否则返回要查找的元素值。
 */
public class L03_Linked04 {
    private int len;    // 链表长度
    private ListNode tailNode; // 链表尾部指针，方便O(1)插入
    private ListNode vHead; //链表虚拟头节点
    public L03_Linked04() {
        // 链表初始时有一个虚拟头节点，链表(业务层)长度为0
        this.len = 0;
        this.vHead = new ListNode(-1, null);
        this.tailNode = this.vHead;
    }

    public void add(int value) {
        ListNode node = new ListNode(value);
        this.tailNode.next = node;
        this.tailNode = node;
        this.len += 1;
    }

    public boolean add(int index, int value) {
        /**
         * index的概念是针对业务链表的部分的！！从业务链表的头节点开始算为0
         * len = 0的时候，只有vHead，业务链表没有index；
         * len = 1的时候，业务链表有 index=0
         * 插入的时候，index可以是0~len；
         * index总是代表在该位置后面做插入，0代表往链表head前面插入，len代表链表尾部插入
         * 思路：找到要被插的节点的前驱节点，虚拟头节点法
         * 正常case: 链表有三个节点，要插入中间
         * 边界case：
         * 1、头节点前插入
         * 2、尾部插入
         *
         * 注意: len 要+ 1
         */
        if (index < 0 || index > this.len) return false;

        ListNode newNode = new ListNode(value);
        ListNode prev = this.vHead;
        ListNode p = this.vHead.next;
        int i = 0;
        while (i < index) {
            prev = p;
            p = p.next;
            i += 1;
        }
        prev.next = newNode;
        newNode.next = p;
        this.len += 1;
        // 如果是尾部插入，要更新tail节点
        if (p == null) this.tailNode = newNode;
        return true;
    }

    public int remove(int index) {
        /**
         * 删除的时候，index=len时已经没有节点了
         * 思路：依然是找prev节点，配合虚拟头节点技巧兼容链表头节点操作的case
         * 正常case：xxx
         * 注意：删除元素之后len-1！！！！
         */
        if (index >= this.len || index < 0) return -1;

        ListNode prev = this.vHead;
        ListNode p = this.vHead.next;
        int i = 0;
        while (i < index) {
            prev = p;
            p = p.next;
            i += 1;
        }
        prev.next = p.next;
        this.len -= 1;
        // 如果是尾节点的删除，要更新tail节点
        if (p == this.tailNode) this.tailNode = prev;
        return p.val;
    }

    public int get(int index) {
        if (index >= this.len || index < 0) return -1;

        ListNode p = this.vHead.next;
        int i = 0;
        while (i < index) {
            p = p.next;
            i += 1;
        }
        return p.val;
    }
}
