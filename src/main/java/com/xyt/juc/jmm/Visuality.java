package com.xyt.juc.jmm;

import java.util.concurrent.locks.LockSupport;

/**
 * 可见性测试
 * @Author: xyt
 * @Date: 2021/12/9 22:40
 * @version: 1.0.0
 */
public class Visuality {

    /**
     *  one thread use this variable , but other try to modify this value
     *
     *  1. volatile
     *  2. The memory barrier (内存屏障)
     */
    private boolean flag = true;
    private int count = 0;

    private void refresh(){
        // modify flag value to false
        flag = false;
        System.out.println(Thread.currentThread().getName() + "=======flag is changed========");
    }

    private void load(){
        while (flag){
            // business logic
            count ++;
            // 可以跳出循环的情况：
            // 1. 在 flag 上增加 volatile 关键字 可以跳出循环 hotspot -> Jvm级别的内存屏障 （storeLoad） -> （汇编层面指令） lock; addl $0,0($$rsp)
            //      他们认为lock指令要比内存屏障性能更好 lock前缀指令不是内存屏障的指令，但是有内存屏障的效果，
            //      使用lock
            // 2. 在 count 上增加 volatile 关键字 ， 同样可以跳出循环
            // 3. 将count的int 改为Integer 同样可以跳出循环
            //      Integer 在拆包的时候 会new Integer(value) 而这个value是 final修饰的， Jvm对final做了优化 同样可以保证可见性
            // 4. by the memory barrier（内存屏障）
            // UnsafeFactory.getUnSafe().storeFence();
            // 5. 可以跳出循环 yield() 释放时间片 -> 上下文切换,在重新加载上下文的时候 回去重新加载flag
            // Thread.yield();
            // 6. 可以跳出循环， println() 使用了： synchronized --> 最终会调用到storeFence()； 也就是说 同样是调用了内存屏障
            // System.out.println(count);
            // 7. 可以将此处等待1微秒 ..等等， 亦可退出循环， 因为  在微秒级别， 此处引用的flag变量 缓存已经被刷新。
            // 8. 内存屏障
            // LockSupport.unpark(Thread.currentThread());
            //  try {
            //       // 9. 同样可以保证可见性， 内存屏障
            //       Thread.sleep(1);
            //   } catch (InterruptedException e) {
            //       e.printStackTrace();
            //  }

            // 总结： Java中可见性如何保证？ 基本上是两种方式：
            // 1. storeLoad 内存屏障，这个是Jvm层面保证的， 从硬件 汇编层面上是通过 lock前缀指令代替了 内存屏障mfence，
            // 2. 线程的上下文切换

            // java
            // volatile  lock
            // 当前线程对共享变量的操作会存在读不到、或者不能立即读到其他线程对此变量的写操作。
        }
        System.out.println(Thread.currentThread().getName() + "========flag is changed , so the circulation is stopped========");
        System.out.println(" count :" + count);
    }

    public static void main(String[] args) {
        Visuality visuality = new Visuality();

        try {
            Thread t1 = new Thread(() -> {
                 visuality.load();
            });
            t1.start();
            // sleep sometime
            Thread.sleep(1000);

            Thread t2 = new Thread(() -> {
                visuality.refresh();
            });
            t2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
