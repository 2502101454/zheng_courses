package org.algorithm_base.exercise;

/**
 只用一个数组来实现三个栈，支持操作如下：

 入栈 push(stackID, value) ，如果栈满了，则返回false，否则返回true。
 出栈 pop(stackID) ，如果栈为空，则返回-1，否则返回出栈元素值。
 查看栈顶 peek(stackID) ，如果栈为空，则返回-1，否则返回栈顶元素值。
 查看栈是否为空 isEmpty(stackID)。

 在上述操作中，stackID表示栈的编号（取值为0、1、2），value表示压入的元素值。
 构造函数会传入一个stackSize参数，代表每个栈的大小。
 */
public class L04_Stack04V2 {
    // 定义三个栈的栈顶指针，因为每个栈都会有自己的出入栈操作，需要单独维护
    private int top1;
    private int top2;
    private int top3;
    int[] data;
    int size;

    public L04_Stack04V2(int stackSize) {
        this.size = stackSize;
        this.data = new int[stackSize * 3];
        this.top1 = -1;
        this.top2 = -1 + stackSize;
        this.top3 = -1 + 2*stackSize;

        // 每个栈的索引范围得定义呀：用于后面判断每个栈操作是否满足，栈的左右边界是不受top影响的
        // 我们定义每个栈的范围都是两头闭区间 [stackID * size, （stackID + 1） * size - 1 ]
    }
    // 获取栈顶指针
    public int getTopByStackID(int stackNum) {
        int tmp = -1;
        switch (stackNum) {
            case 0: {
                tmp = this.top1;
                break;
            }
            case 1: {
                tmp = this.top2;
                break;
            }
            case 2:{
                tmp = this.top3;
                break;
            }
            default: tmp = -2;
        }
        return tmp;
    }

    /**
     * 更新对应栈的top指针
     * @param stackNum
     * @param delta
     * @return
     */
    public void updateTopByStackID(int stackNum, int delta) {
        switch (stackNum) {
            case 0: this.top1 += delta; break;
            case 1: this.top2 += delta; break;
            case 2: this.top3 += delta; break;
        }
    }

    // 先申请位置
    public boolean push(int stackNum, int value) {
        int tmp = this.getTopByStackID(stackNum);
        // 探查 <<要尝试的位置>> 是否符合当前栈的范围
        tmp += 1;
        // 插入时，判断新的栈顶指针不能超过右边界
        int end = (stackNum + 1) * size - 1;
        if (tmp <= end) {
            // 符合，则入栈，同时栈顶指针更新
            this.data[tmp] = value;
            this.updateTopByStackID(stackNum, 1);
        }
        return false;
    }

    public int pop(int stackNum) {
        // 获取栈顶指针
        int tmp = this.getTopByStackID(stackNum);
        // 出栈时，判定当前栈顶不能超过左边界
        int begin = stackNum * this.size;
        if (tmp >= begin) {
            this.updateTopByStackID(stackNum, -1);
            return this.data[tmp];
        }

        return -1;
    }

    public int peek(int stackNum) {
        int tmp = this.getTopByStackID(stackNum);
        // 判定当前栈顶不能超过左边界
        int begin = stackNum * this.size;
        if (tmp >= begin) {
            return this.data[tmp];
        }

        return -1;
    }

    public boolean isEmpty(int stackNum) {
        int tmp = this.getTopByStackID(stackNum);
        // 判定当前栈顶不能超过左边界
        int begin = stackNum * this.size;
        if (tmp >= begin) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        L04_Stack04V2 stack04 = new L04_Stack04V2(1);
        stack04.push(0, 2);
        stack04.push(1, 2);
        stack04.push(2, 2);
        System.out.println(stack04.push(2, 2));
        int pop = stack04.pop(2);
        System.out.println(pop);

        System.out.println("==================");
        System.out.println("top1:" + stack04.top1 + " top2:" + stack04.top2 + " top3:" + stack04.top3);
        for(int x: stack04.data) {
            System.out.println(x);
        }
        System.out.println("=========================");
        System.out.println(stack04.isEmpty(0));
    }
}
