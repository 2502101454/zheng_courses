package org.drafts;

public class array_test {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5};
        // for 循环打印数组
        for (int i = 0; i < a.length; i++) {
            System.out.println(i + ":" + a[i]);
        }
    }
}
