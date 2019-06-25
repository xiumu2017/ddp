package com.paradise.thread;

/**
 * @author Paradise
 */
public class HomeThread extends Thread{
    public static void main(String[] args) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
