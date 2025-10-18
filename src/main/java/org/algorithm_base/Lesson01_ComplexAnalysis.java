package org.algorithm_base;

/**
 * @author zengwang
 * @create 2024-06-29 10:21
 * @desc:
 */
public class Lesson01_ComplexAnalysis {
    public static void main(String[] args) {
        // 一段逻辑中，执行次数最多的代码就代表该逻辑的时间复杂度
//        System.out.println(new Lesson01_ComplexAnalysis().sumN(400));
        System.out.println(new Lesson01_ComplexAnalysis().sumN2(400));
    }

    /**
     * 假设n = 4:
     * 1*1 + 1*2 + 1*3 + 1*4 = 1*(1+2+3+4)
     *             add
     * 2*1 + 2*2 + 2*3 + 2*4 = 2*(1+2+3+4)
     *             add
     * ....                  = 3*(1+2+3+4)
     *
     * 复杂度为  O(n^2)， 优化：可以共享(1+2+3+4)  ！！
     * @param n
     * @return
     */
    public long sumN(int n) {
        long start = System.currentTimeMillis();
        long result = 0;
        for (int i=1; i <= n; i++) {
            for (int j=1; j<= n; j++) {
                result = result + i*j;
            }
        }
        System.out.println("sumN: " + (System.currentTimeMillis() - start));
        return result;
    }

    /**
     * 复杂度为  O(n)
     * @param n
     * @return
     */
    public long sumN2(int n) {
        long start = System.currentTimeMillis();
        long result = 0;
        // 提前计算被共享的(1+2+3+4)
        int tmp = 0;
        for (int i=1; i<= n; i++) {
            tmp += i;
        }
        for (int j=1; j <= n; j++) {
            result += j*tmp;
        }
        System.out.println("sumN2: " + (System.currentTimeMillis() - start));
        return result;
    }

    /**
     * 求 第一个比n大的，且是3的K次方的数
     * @param n
     * @return
     */
    public long func(int n) {
        int result = 1; // 3^0
        // 复杂度分析：主要看 while 循环中执行的次数
        while (result <= n) {
            result = result * 3;
        }
        return result;
    }

    public void reverse(int []arr) {
        for(int i=0; i<arr.length/2; i++) {
            int tmp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = tmp;
        }
    }
}
