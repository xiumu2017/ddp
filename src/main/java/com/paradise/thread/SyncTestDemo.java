package com.paradise.thread;

/**
 * @author Paradise
 */
public class SyncTestDemo {
    public static void main(String[] args) {
        final SyncDemo syncDemo = new SyncDemo();
        Thread thread1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            syncDemo.addValue();
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

        Thread thread2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            syncDemo.addValueLock();
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

        thread1.start();
        thread2.start();
    }
}
