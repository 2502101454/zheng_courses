package org.algorithm_base.exercise;

/**
 * @author zengwang
 * @create 2025-02-20 17:35
 * @desc:
 */

/**
 有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。在领养该收容所的动物时，领养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，或者指定领养种类猫或狗（同时必须收养此类动物中“最老”的）。
 换言之，收养人不能自由挑选想领养的对象。请创建适用于这个系统的数据结构，实现如下几个操作方法：

 enqueueDog(dogID) 如果收容所满了，则返回false，否则返回true。
 enqueueCat(catID) 同上。
 dequeueAny() 如果收容所为空，则返回-1，否则返回动物编号ID。
 dequeueDog() 如果收容所中没有狗狗了，则返回-1，否则返回狗狗编号ID。
 dequeueCat() 如果收容所中没有猫猫了，则返回-1，否则返回猫猫编号ID。
 对于以上操作的说明

 dogID 和 catID 分表代表狗猫的编号；
 收容所的大小通过构造函数中的 shelfSize 传递；
 收容所每次只能进行一个操作，也就是说不需要考虑并发问题。
 */
public class L05_Queue04 {
    /**
     * 方案思考：
     *  1.用一个数组代表整个收容所，即划分为两个队列，猫、狗，但是人家说如果猫队列出去了一个了，狗队列已经满了的话，在收狗还是可以的，看整体空间
     *  所以区分队列的方式是不合适的！！
     *  2.只能使用混在一起的方式，只有一个队列：
     *    2.2 用数组实现，当dequeue指定类型的时候，造成数组碎片，需要移动数据，也不合适，而且需要标识猫、狗，需要额外创建class
     *    2.2 用链表实现，天生封装了对象，且dequeue任一类型的数据，不存在碎片
     *
     * 采用2.2
     */
    private static class AnimalNode {
        public int id;
        public char kind; // 'c' cat; 'd' dog
        public AnimalNode next;

        public AnimalNode() {

        }
        public AnimalNode(int id, char kind) {
            this.id = id;
            this.kind = kind;
        }
    }

    public int size;
    public int count;
    public AnimalNode head;
    public AnimalNode tail;

    public L05_Queue04(int shelfSize) {
        size = shelfSize;
    }

    public boolean enqueueAnimal(AnimalNode animalNode) {
        /*
         *我们全局只有一个队列，来了就往后插,不区分动物类型
         */
        // 如果队列已满
        if (count == size) return false;

        // 队列不满：为空
        if (head == null) {
            head = animalNode;
            tail = animalNode;
        } else {
            tail.next = animalNode;
            // 这块逻辑漏掉了，还得是debug牛的
            tail = animalNode;
        }
        count += 1;
        return true;
    }

    public boolean enqueueDog(int dogID) {
        AnimalNode dogNode = new AnimalNode(dogID, 'd');
        return enqueueAnimal(dogNode);
    }

    public boolean enqueueCat(int catID) {
        AnimalNode catNode = new AnimalNode(catID, 'c');
        return enqueueAnimal(catNode);
    }

    public int dequeueAny() {
        // 队列为空
        if (head == null) return -1;

        int id = head.id;
        head = head.next;
        count -= 1;
        return id;
    }

    /**
     * 1.没找到啥也不干；
     * 2.找链表中的动物节点，找到了就删除；
     *   2.1 遇到删除的节点是队列的头节点，更新head指针
     *   2.2 遇到删除的节点是尾节点，更新tail指针
     *   2.3 极端case，队列只有一个节点时，如何更新head、tail
     * @param kind
     * @return
     */
    private int del_by_vnode(char kind) {
        /*
        1.从队列头开始往后找，找到第一个dog的节点，删掉
        2.因为可能删除的是头节点、尾部节点，所以我们使用虚拟头节点
        3.每次都要保证新的虚拟节点指针链接的是最新的head节点
        */
        AnimalNode vNode = new AnimalNode();
        vNode.next = head;

        // p代表前驱节点，q代表当前查找的节点
        AnimalNode p = vNode;
        AnimalNode q = vNode.next;

        while (q != null && q.kind != kind) {
            p = q;
            q = q.next;
        }

        if (q == null) return -1;
        // 找到要删除的节点
        if (q.kind == kind) {
            p.next = q.next;
            count -= 1;
        }
        // 删除的是头节点
        if (q == head) {
            head = vNode.next;
        }
        // 删除的是尾部节点
        if (q == tail) {
            tail = p;
        }

        return q.id;
    }

    public int dequeueDog() {
        return del_by_vnode('d');
    }

    public int dequeueCat() {
        return del_by_vnode('c');
    }

    public void printAnimals() {
        AnimalNode p = head;
        while (p != null) {
            System.out.println(p.kind + ": " + p.id);
            p = p.next;
        }
    }

    public static void main(String[] args) {
        L05_Queue04 queue = new L05_Queue04(3);
        System.out.println(queue.dequeueAny());
        System.out.println(queue.dequeueCat());
        System.out.println(queue.enqueueCat(1));
        System.out.println(queue.enqueueDog(2));
        System.out.println(queue.enqueueCat(3));
        System.out.println(queue.enqueueDog(4)); // false
        System.out.println("------------");
        queue.printAnimals();
        System.out.println("------------");
        System.out.println(queue.dequeueDog());
        System.out.println("------------");
        queue.printAnimals();
        System.out.println("------------");
        System.out.println(queue.dequeueDog());
        System.out.println(queue.dequeueCat());
        System.out.println(queue.dequeueAny());
        System.out.println(queue.dequeueAny());
        System.out.println("------------");
        queue.printAnimals();
        System.out.println("------------");
        queue.enqueueDog(4);
        queue.printAnimals();
    }
}