package com.xyt.juc.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * 中断机制
 * interrupt(): 给线程一个中断标志 不会中断停止线程
 * Thread.interrupted(): 判断当前线程中断标志位是否为true， 并且会清除中断标志，且重置为false
 * isInterrupted(): 判断当前线程中断标志位是否为true 不会清除中断标志位
 * sleep(): 会抛出中断异常， 导致线程中断， 并且会清除中断标志位
 * wait(): 会抛出中断异常，导致线程中断，并且会清除中断标志位
 * @author  xyt
 */
public class ThreadInterruptTest {

    static int i = 0;

    public static void main(String[] args)  {
        System.out.println("begin");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public  void run() {
                while (true) {
                    i++;
                    System.out.println(i);
                    try {
                        // sleep会清除中断标志
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //Thread.interrupted()  清除中断标志位
                    //Thread.currentThread().isInterrupted() 不会清除中断标志位
                    if (Thread.interrupted()  ) {
                        System.out.println("=========");
                    }
                    if(i==10){
                        break;
                    }

                }
            }
        });

        t1.start();
        //不会停止线程t1,只会设置一个中断标志位 flag=true
        t1.interrupt();

    }
}
