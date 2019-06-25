package com.paradise.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两种锁机制
 *
 * @author Paradise
 */
class SyncDemo {
    private int value = 0;
    private Lock lock = new ReentrantLock();
    private boolean flag = false;

    void addValue() {
        if (flag) {
            this.value++;
            System.out.println(Thread.currentThread().getName() + ":" + this.value);
        } else {
            synchronized (this) {
                this.value++;
                System.out.println(Thread.currentThread().getName() + ":" + this.value);
            }
        }

    }

    void addValueLock() {
        try {
            lock.lock();
            this.value++;
            System.out.println(Thread.currentThread().getName() + ":" + this.value);
        } finally {
            lock.unlock();
        }
    }
}
