package org.code_beauty.programmer;

public class HelloWorld extends B {
    {
        System.out.println("HelloWorld instance block");
    }
    public HelloWorld() {
        super(10);
        //6.1
        System.out.println("HelloWorld constructor");
    }

    public String getName() {
        return "HelloWorld";
    }

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
    }
}

class B extends A{
    private int b;

    {
        System.out.println("B instance block");
    }
    public B(int b) {
        super();
        this.b = b;
        System.out.println("B constructor with b param" + b);
    }
    public B(){
        System.out.println("B constructor");
    }
}

class A {
    {
        System.out.println("A instance block");
    }
    public A() { //4.1
        System.out.println("A constructor");
    }
}
