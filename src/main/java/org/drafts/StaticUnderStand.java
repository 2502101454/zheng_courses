package org.drafts;

/**
 * static变量\代码块 只可以在类属性中被创建，它必须和类绑定，被所有的实例共享，可以被任意实例修改后同步给所有实例。
 * 生命周期： 在整个程序的生命周期都存在
 *
 * 类中符号的初始化流程：
 * 1.JVM加载阶段：类的.class文件(一个类生成一个.class)被JVM加载到进程虚拟内存中的，即将类静态和非静态信息都
 *
 *
 *
 *
 */

public class StaticUnderStand {
    static int a = 3;
    static final int b = 1;
    static {
        // 静态块内的局部变量只在该块内有效，出了静态块后无法访问
        char c = 'c';
        String d = "dd";
    }
    int e = 5;

    public static void main(String []args) {
//        System.out.println(StaticUnderStand.a); // 3
//        StaticUnderStand ss = new StaticUnderStand();
//        System.out.println(ss.a); // 3，对象也能调用静态变量，但是语法上不推荐
//
//          System.out.println(StaticOrder.b);
        StaticOrder staticOrder = new StaticOrder();
        staticOrder.print();
        staticOrder.hello();
//        StaticOrder staticOrder2 = new StaticOrder();
//            Teacher t = new Teacher(20, "wz", "Program-teacher");
//
//        HaHa haha = new HaHa(1, "haha");
//        System.out.println(haha.getAddress());
    }
}

// 理解静态变量、普通变量、构造函数，它们之间的向后执行顺序

/**
 * Java中 static 变量、static代码块，类变量、类内部代码块、构造函数、函数，执行的顺序是？
 * static相关先执行：内部，谁写前面谁先执行
 * 类相关再执行：内部，谁写前面谁先执行
 * 对象生成梯队再执行：构造函数 》普通函数调用
 */

class Person {
    private int age;
    private String name;

    public Person() {}

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
}

class Teacher extends Person{
    private String job;

    public Teacher() {
        super();
        this.job = "en-teacher";
    }

    public Teacher(int age, String name, String job) {
        super(age, name);
        this.job = job;
    }
}


class StaticMain {
    {
        System.out.println("normal-father");
    }
    public int f_a = new A().getVal("father");


    public static int b = new B().getVal("father");
    static {
        System.out.println("StaticMain");
    }
    public StaticMain() {
        System.out.println("StaticMain constructor");
    }

    public void hello() {
        System.out.println("hello from father");
    }
}
class StaticOrder extends StaticMain {
    public int a = new A().getVal("son");
    {
        System.out.println("normal-son");
    }

    static {
        System.out.println("staticOrder");
    }
    public static int b = new B().getVal("son");

    public StaticOrder() {
        super();
        System.out.println("StaticOrder constructor");
    }

    public void print() {
        System.out.println("StaticOrder instance func");
    }
}

class A {
    public int getVal(String msg) {
        System.out.println("A by " + msg);
        return 1;
    }
}

class B {
    public int getVal(String msg) {
        System.out.println("B by " + msg);
        return 1;
    }
}
// 构造器中可以调用实例方法
