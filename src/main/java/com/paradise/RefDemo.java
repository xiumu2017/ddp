package com.paradise;

/**
 * @author Paradise
 */
public class RefDemo {
    private static void change(StringBuilder s1, StringBuilder s2) {
        s1.append("...");
        s2 = s1;
    }

    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder("Hello");
        StringBuilder s2 = new StringBuilder("Hello");
        change(s1, s2);
        System.out.println(s2);
    }
}
