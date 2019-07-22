package com.paradise.interview.concurrent.demo;

/**
 * @author Paradise
 */
public class TaskMain {
    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        Thread t1 = new Thread(myTask);
        Thread t2 = new Thread(myTask);
        Thread t3 = new Thread(myTask);
        Thread t4 = new Thread(myTask);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        myTask.get();

    }
}
