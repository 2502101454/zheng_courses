package org.algorithm_base.exercise;
// 实现顺序栈
public class L04_Stack01 {
    private int size; // 代表栈大小
    private int[] stack; // 所以说清楚自己程序的每个变量的业务意义很重要，已知维护好它们，思路就清晰
    private int top; // 设top为当前栈顶元素的 index

    public L04_Stack01(int stackSize) {
        this.stack = new int[stackSize];
        this.size = stackSize;
        this.top = -1; // 栈中元素为空的时候，栈顶不可指向元素，因此是-1
    }

    // 先定位子再入座的思路, 位子从0开始
    public boolean push(int value) {
        // 每插入一个元素，先更新栈顶指针，再插入
        this.top += 1;
        if (this.top <= this.size - 1) {
            this.stack[this.top] = value;
            return true;
        }

        // 定超范围了，修改回去
        this.top -= 1;
        return false;
    }

    public int pop() {
        if (this.top == -1) {
            return -1;
        }
        int data = this.stack[this.top];
        this.top -= 1;
        return data;
    }

    public int peek() {
        if (this.top == -1) {
            return -1;
        }
        return this.stack[this.top];
    }

    public boolean isEmpty() {
        if (this.top == -1) {
            return true;
        }
        return false;
    }
}
