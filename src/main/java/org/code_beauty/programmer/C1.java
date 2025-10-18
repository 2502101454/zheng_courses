package org.code_beauty.programmer;

public class C1 {
    public static void main(String[] args) {
        byte a = 12;
        byte b = 10;
        int d = a + b; // 因为a、b临时变成int类型，计算的结果也就是int类型了，所以强转为byte
        System.out.println(d); // 22, byte
        System.out.println(a + b); // 22, int

        float f1 = 100000000.0f;
        System.out.println("" + f1); //1.0E8
        // float f2 = 1.235 * 10^7; // 编译器可不认识^
        float f2 = 1.235E4f; // 编译器认识E
        System.out.println(f2); // 12350.0
}
}
