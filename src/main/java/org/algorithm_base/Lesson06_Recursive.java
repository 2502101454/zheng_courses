package org.algorithm_base;

public class Lesson06_Recursive {
    private int [] mem;

    /**
     * 将递归函数的备忘录 和 递归调用，放在一起初始化，套一层非递归的壳，代码层次更清晰
     * @param n
     * @return
     */
    public int f(int n) {
        mem = new int[n + 1];
        return f_r(n);
    }

    /** 画图走一波就明白了；
     * 在 “归”的时候，即弹栈的时候，把f(n)的mem[n]完成赋值
     * @param n
     * @return
     */
    private int f_r(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (mem[n] != 0) return mem[n];
        mem[n] = f_r(n-1) + f_r(n-2);
        return mem[n];
    }

    public static void main(String[] args) {
        Lesson06_Recursive re = new Lesson06_Recursive();
        int sum = re.f(5);
        System.out.println(sum);

    }
}
