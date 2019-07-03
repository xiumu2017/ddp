package com.paradise.interview.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Paradise
 */
public class TestMain {
    public static void main(String[] args) {
        int x;
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = new DemoInputStream(new BufferedInputStream(new FileInputStream("E:\\1.txt")));
            while ((x = inputStream.read()) > 0) {
                // System.out.print((char) x);
                stringBuilder.append((char) x);
            }
            System.out.println(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
