package com.paradise.interview.concurrent.cycle;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Paradise
 */
public class MyLock {

    public static void main(String[] args) throws InterruptedException {
        Account src = new Account(10000);
        Account target = new Account(10000);
        CountDownLatch countDownLatch = new CountDownLatch(9999);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
//        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        for (int i = 0; i < 9999; i++) {
            singleThreadPool.execute(() -> {
                src.transactionToTarget(1, target);
                countDownLatch.countDown();
            });

//            new Thread(() -> {
//                src.transactionToTarget(1, target);
//                countDownLatch.countDown();
//                System.out.println(Thread.currentThread().getName());
//            }).start();
        }
        singleThreadPool.shutdown();
        countDownLatch.await();
        System.out.println("src=" + src.getBalance());
        System.out.println("target=" + target.getBalance());
    }

    /**
     * 账户类
     */
    static class Account {
        // 构造方法
        Account(Integer balance) {
            this.balance = balance;
        }

        private Integer balance;

        //转账方法
        void transactionToTarget(Integer money, Account target) {
            Allocator.getInstance().apply(this, target);
            this.balance -= money;
            target.setBalance(target.getBalance() + money);
            Allocator.getInstance().release(this, target);
        }

        Integer getBalance() {
            return balance;
        }

        void setBalance(Integer balance) {
            this.balance = balance;
        }
    }

    /**
     * 单例锁类
     */
    static class Allocator {
        private Allocator() {
        }

        private List<Account> locks = new ArrayList<>();

        // 申请资源
        synchronized void apply(Account src, Account tag) {
            while (locks.contains(src) || locks.contains(tag)) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            locks.add(src);
            locks.add(tag);
        }

        // 释放资源
        synchronized void release(Account src, Account tag) {
            locks.remove(src);
            locks.remove(tag);
            this.notifyAll();
        }

        // 获得单例对象
        static Allocator getInstance() {
            return AllocatorSingle.install;
        }

        /**
         * 单例模式的一种方式，内部类？
         */
        static class AllocatorSingle {
            static Allocator install = new Allocator();
        }
    }
}

