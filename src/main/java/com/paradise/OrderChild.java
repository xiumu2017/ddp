package com.paradise;

/**
 * @author Paradise
 */
public class OrderChild extends OrderBase {

    static {
        System.out.println("Child static block - 子类静态代码块");
    }

    {
        System.out.println("Child block - 子类代码块1");
    }

    public OrderChild() {
        super("11");
        System.out.println("Child constructor - 子类构造方法");
    }


    {
        System.out.println("Child block - 子类代码块2");
    }

    public static void main(String[] args) {
        System.out.println("...");
        OrderChild child = new OrderChild();
    }

}
