package com.paradise.jk;

import java.util.ArrayList;
import java.util.List;

public final class JkFinalDemo {
    public final Integer integer = 1;

    public final void run() {
        System.out.println(">>>");
    }

    public static void main(String[] args) {
        final List<String> strList = new ArrayList<>();
        strList.add("Hello");
        strList.add("world");
        System.out.println(strList.size());
//        List<String> unmodifiableStrList = List.of("hello", "world");
//        unmodifiableStrList.add("again");
        StringBuilder stringBuilder = new StringBuilder(5);
        stringBuilder.append("zzzzz").append("xxxx");
        System.out.println(stringBuilder.toString());
        String s = "zzz".intern();
        Integer integer = new Integer(1);
        Object o;
        List<IncompatibleClassChangeError> list = new ArrayList<>(20);
    }
}
