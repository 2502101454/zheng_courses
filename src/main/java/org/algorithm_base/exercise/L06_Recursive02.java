package org.algorithm_base.exercise;

/**
 * 给定一个 n，递归求 n!（即 n 的阶乘）。
 *
 * 为了避免答案超32位整数范围，因此，在计算的过程中，答案需要与 7777777 取模，如计算结果为：7777778，则返回 1。
 */
public class L06_Recursive02 {
    // 不用备忘录：因为递归是线性的，不存在重复子问题
    public int factorial(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return (n * factorial(n - 1)) % 7777777;
    }
    public static void main(String[] args) {
        L06_Recursive02 l06Recursive02 = new L06_Recursive02();
        System.out.println(l06Recursive02.factorial(4));
    }
}
