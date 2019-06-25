package com.paradise;

/**
 * @author Paradise
 */
public class OrderBase {
    static {
        System.out.println("Father static block - 父类静态代码块");
    }

    {
        System.out.println("Father - 父类代码块");
    }

    public OrderBase(String s) {
        System.out.println("Father constructor - 父类构造方法");
        System.out.println(s);
    }

    public static void main(String[] args) {
        System.out.println("main fun - main方法执行");
        OrderChild orderChild = new OrderChild();
    }

}
