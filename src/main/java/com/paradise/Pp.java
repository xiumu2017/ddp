package com.paradise;

/**
 * @author Paradise
 */
public class Pp {
    public static void main(String[] args) {
        int x = 1;
        int y = 2;
        int z = 3;
//        int m =  z-- / ++x;
        int n =  --z / ++x;

        System.out.println(n);
        System.out.println(z);
    }
}
