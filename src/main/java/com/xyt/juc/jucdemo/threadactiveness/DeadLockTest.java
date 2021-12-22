package com.xyt.juc.jucdemo.threadactiveness;

import lombok.extern.slf4j.Slf4j;

/**
 * @author  Fox
 * 死锁
 */
@Slf4j
public class DeadLockTest {

    private static String a = "a";
    private static String b = "b";

    public static void main(String[] args) {
        Thread threadA = new Thread(()->{
            synchronized (a) {
                log.debug("threadA进入a同步块，执行中...");
                try {
                    Thread.sleep(2000);
                    synchronized (b) {
                        log.debug("threadA进入b同步块，执行中...");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"threadA");

        Thread threadB = new Thread(()->{
            synchronized (b) {
                log.debug("threadB进入b同步块，执行中...");
                synchronized (a) {
                    log.debug("threadB进入a同步块，执行中...");
                }
            }
        },"threadB");

        threadA.start();
        threadB.start();

    }

}
