package com.xyt.juc.jucdemo;

/**
 * @Author: xyt
 * @Date: 2022/1/25 9:58
 * @version: 1.0.0
 */
public class ThreadTest {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(5000);

                System.out.println("线程A执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A");
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                thread1.join();
                System.out.println("线程B执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B");
        thread1.start();
        thread2.start();

    }
}
