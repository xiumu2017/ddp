package com.paradise.interview;

import java.util.HashMap;
import java.util.Map;

public class HashDemo {

    private String name;

    private HashDemo(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println(1 << 5);
        char[] chars = {'c'};
        System.out.println(chars.toString());
        HashDemo hashDemo = new HashDemo("demo1");
        System.out.println(hashDemo.toString());
        Map<String, HashDemo> map = new HashMap<>();
        map.put("", hashDemo);
        map.put(null, new HashDemo("demo2"));
        map.put(null, new HashDemo("demo3"));
        System.out.println(map.get(null).toString());
    }

    @Override
    public String toString() {
        return name;
    }
}
