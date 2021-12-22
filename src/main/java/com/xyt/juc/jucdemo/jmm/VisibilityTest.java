package com.xyt.juc.jucdemo.jmm;

/**
 * @author Fox
 *
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp
 * hsdis-amd64.dll
 *  可见性案例
 */
public  class VisibilityTest {
    //  storeLoad  JVM内存屏障  ---->  (汇编层面指令)  lock; addl $0,0(%%rsp)
    // lock前缀指令不是内存屏障的指令，但是有内存屏障的效果   缓存失效
    private boolean flag = true;
    private int count = 0;

    public void refresh() {
        // threadB对flag的写操作会 happens-before threadA对flag的读操作
        flag = false;
        System.out.println(Thread.currentThread().getName() + "修改flag:"+flag);
    }

    public void load() {
        System.out.println(Thread.currentThread().getName() + "开始执行.....");
        while (flag) {
            //TODO  业务逻辑
            count++;
            //JMM模型    内存模型： 线程间通信有关   共享内存模型
            //没有跳出循环   可见性的问题
            //能够跳出循环   内存屏障
            //UnsafeFactory.getUnsafe().storeFence();
            //能够跳出循环    ?   释放时间片，上下文切换   加载上下文：flag=true
            //Thread.yield();
            //能够跳出循环    内存屏障
            //System.out.println(count);

            //LockSupport.unpark(Thread.currentThread());

            //shortWait(1000000); //1ms
            //shortWait(1000);
//            long start = System.nanoTime();
//            long end = 0;
//            // do while   while?
//            while (start + 1000000 >= end) {
//                end = System.nanoTime();
//            }

//            try {
//                Thread.sleep(1);   //内存屏障
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            //总结：  Java中可见性如何保证？ 方式归类有两种：
            //1.  jvm层面 storeLoad内存屏障    ===>  x86   lock替代了mfence
            // 2.  上下文切换   Thread.yield();

            // java
            // volatile  锁机制
            //当前线程对共享变量的操作会存在读不到，或者不能立即读到另一个线程对此变量的写操作

            // lock 硬件层面扩展      JMM为什么选择共享内存模型

        }
        System.out.println(Thread.currentThread().getName() + "跳出循环: count=" + count);
    }

    public static void main(String[] args) throws InterruptedException {
        VisibilityTest test = new VisibilityTest();

        // 线程threadA模拟数据加载场景
        Thread threadA = new Thread(() -> test.load(), "threadA");
        threadA.start();

        // 让threadA执行一会儿
        Thread.sleep(1000);
        // 线程threadB通过flag控制threadA的执行时间
        Thread threadB = new Thread(() -> test.refresh(), "threadB");
        threadB.start();

    }


    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}
