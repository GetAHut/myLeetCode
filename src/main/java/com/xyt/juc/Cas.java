package com.xyt.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: xyt
 * @Date: 2021/12/15 21:21
 * @version: 1.0.0
 */
public class Cas {

    private volatile static int sum = 0;
    //空对象锁
    private static Object object = "";
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            // volatile 可以保证可见性、有序性，但是不能保证原子性
            // 所有volatile 是线程不安全的
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 10000; j++) {
//                    sum++;
//                }
//            });
//            thread.start();
            // 1. 保证线程安全的方式有： synchronized
//                Thread thread1 = new Thread(() -> {
//                    synchronized (object){
//                        for (int j = 0; j < 10000; j++) {
//                            sum++;
//                        }
//                    }
//                });
//                thread1.start();
            // 2. 通过ReentrantLock lock 实现加锁
//            Thread thread2 = new Thread(() -> {
//                lock.lock();
//                try {
//                    for (int j = 0; j < 10000; j++) {
//                        sum++;
//                    }
//                } finally {
//                    // 解锁逻辑 必须在finally中
//                    lock.unlock();
//                }
//
//            });
//            thread2.start();
            // 3. 可以通过CAS实现
            Thread thread3 = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
//                    sum++;
                    count.incrementAndGet();
                }

            });
            thread3.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }
}
