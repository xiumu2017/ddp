package com.paradise.interview.concurrent.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Paradise
 */
public class MyTask implements Runnable {

    private int x = 10;

    private synchronized void decrease() {

    }

    private final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        this.lock.tryLock();
        try {
            while (x > 0) {
                System.out.println(Thread.currentThread().getName() + " -- " + --x);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {

            lock.unlock();
        }
    }

    public void get() {
        System.out.println(x);
    }
}
