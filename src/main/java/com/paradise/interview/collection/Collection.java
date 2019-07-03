package com.paradise.interview.collection;

import java.util.*;

/**
 * @author Paradise
 */
public class Collection {
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        list.add("123");
        Collections.addAll(list, args);
        Map<String, Object> map = Collections.synchronizedMap(new HashMap<>(16));
        map.put("123", list);
    }
}
