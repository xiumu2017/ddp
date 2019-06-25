package com.paradise.thread;

/**
 * @author Paradise
 */
public class JoinDemo {
   public class SmallThread_ implements Runnable {

        @Override
        public void run() {
            System.out.println("SmallThread_ run >>>");
        }
    }

   public class SmallThread$ implements Runnable {

        @Override
        public void run() {
            System.out.println("SmallThread$ run >>>");
        }
    }

    private void tt(){
        Thread thread1 = new Thread(new SmallThread_());
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
