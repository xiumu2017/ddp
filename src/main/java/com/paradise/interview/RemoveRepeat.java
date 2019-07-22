package com.paradise.interview;

/**
 * 整数去重-软通的一道面试题目
 *
 * @author Paradise
 */
public class RemoveRepeat {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5, 6, 6, 6, 7, 8, 9, 0};

        boolean[] booleans = new boolean[100];

        for (int temp : arr) {
            if (booleans[temp]) {
                System.out.println(temp + " - " + temp);
            } else {
                booleans[temp] = true;
            }

        }
    }
}
