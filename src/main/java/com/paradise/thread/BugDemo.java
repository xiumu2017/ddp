package com.paradise.thread;

public class BugDemo {
    private static long count = 0;

    public static long getCount() {
        return count;
    }

    private synchronized void add10K() {
        System.out.println("current count " + count);
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }

    public static long calc() throws InterruptedException {
        final BugDemo test = new BugDemo();
        // 创建两个线程，执行 add() 操作
        Thread th1 = new Thread(() -> {
            test.add10K();
        });
        Thread th2 = new Thread(() -> {
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return count;
    }

    public static void main(String[] args) {
        try {
            calc();
            System.out.println(BugDemo.count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
