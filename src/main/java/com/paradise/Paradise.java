package com.paradise;

import java.util.ArrayList;
import java.util.List;

/**
 * 静态代码块在类加载时执行
 * main方法在类加载完成后执行
 *
 * @author Paradise
 */
public class Paradise {
    static {
        System.out.println("----");
    }

    public static void main(String[] args) {
        System.out.println(">>>");
        List<String> list = new ArrayList<>();
    }

    public void run() {

    }

    public int run(int x) {
        return 1;
    }

    public String run(String s) {
        return "s";
    }
}
