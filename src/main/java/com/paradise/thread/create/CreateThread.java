package com.paradise.thread.create;

import java.util.concurrent.Callable;

/**
 * 多种创建线程的方式
 *
 * @author Paradise
 */
public class CreateThread {

    public class Thread1 extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("Thread1");
        }
    }

    public class Thread2 implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread2");
        }
    }


    public static class Thread3 implements Callable {

        @Override
        public Object call() throws Exception {
            return null;
        }
    }

    private void test(){
        Thread thread1 = new Thread1();
        thread1.start();

        Thread thread2 = new Thread(new Thread2());
        thread2.start();
    }

    public static void main(String[] args) {
        new CreateThread().test();
    }
}
