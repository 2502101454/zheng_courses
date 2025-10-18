package org.algorithm_base.exercise;

/**
 * 斐波那契数列是一种特殊的数列，其相邻数之间存在以下关系（F(n)表示第n个数）：
 *
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 初始值如下：
 *
 * F(1) = 1, F(2)=1
 * 给定 n ，请计算 F(n) 。
 *
 * 为了避免答案超32为整数范围，因此，在计算的过程中，答案需要与 1000000007 取模，如计算结果为：1000000008，请返回 1。
 *
 * 0 <= n <= 100
 */

public class L06_Recursive01 {
    private int [] m; // 用备忘录：因为递归是树状的，且存在重复子问题
    public int fibonacci(int n) {
        m = new int[n + 1];
        return recur(n);
    }

    public int recur(int n) {
        if (n == 1) return 1;
        if (n == 2) return 1;
        // 如果备忘录有值，就直接取出来，不要做重复计算
        if (m[n] > 0) return m[n];
        // 备忘录没有，就去算
        m[n] = (recur(n-1) + recur(n-2)) % 1000000007;

        return m[n];// 拿到值后返回上一次执行的地址去继续递归过程
    }

    public static void main(String[] args) {
        L06_Recursive01 l06Recursive01 = new L06_Recursive01();
        int i = l06Recursive01.fibonacci(5);
        System.out.println(i);
    }
}
