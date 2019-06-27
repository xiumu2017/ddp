package com.paradise;

/**
 * @author Paradise
 */
public class RefDemo {
    public static void change(StringBuilder s1, StringBuilder s2) {
        s1.append(">>>");
        System.out.println(s2);
        s2 = new StringBuilder("...");
        System.out.println(s2);
    }

    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder("|||");
        StringBuilder s2 = new StringBuilder("|||");
        change(s1, s2);
        System.out.println(s1);
        System.out.println(s2);
    }
}
