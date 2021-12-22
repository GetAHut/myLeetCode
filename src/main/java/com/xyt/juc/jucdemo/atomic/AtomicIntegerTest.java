package com.xyt.juc.jucdemo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Fox
 */
public class AtomicIntegerTest {

    static AtomicInteger sum = new AtomicInteger(0);

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    // 原子自增  CAS
                    sum.incrementAndGet();
                    //count++;

                }
            });
            thread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum.get());

    }

}
