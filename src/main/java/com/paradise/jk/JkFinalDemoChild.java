package com.paradise.jk;

/**
 * 无法继承final修饰的方法
 *
 * @author Paradise
 */
public class JkFinalDemoChild {
    public static void main(String[] args) {
        JkFinalDemo jkFinalDemo = new JkFinalDemo();
        jkFinalDemo.run();
        System.out.println(" 原始值： " + jkFinalDemo.integer);
        //        jkFinalDemo.integer = 2; // 无法修改final修饰的变量
        // 尝试通过调用方法修改
        JkFinalDemoChild.changeFinal(jkFinalDemo.integer);
        System.out.println("after changeFinal fun() : " + jkFinalDemo.integer);
    }

    private static void changeFinal(Integer x) {
        x = 3;
        System.out.print(">>> changeFinal: x = ");
        System.out.println(x);
    }

    private static void changeFinalParameter(final Integer x) {
        //        x = 3;
        //        Cannot assign a value to final variable 'x'
        String str;
        System.out.println(x);
    }
}
