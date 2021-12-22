package com.xyt.juc.jucdemo.jmm;

import java.util.concurrent.locks.ReentrantLock;

import com.xyt.juc.jucdemo.lock.CASLock;

/**
 * @author Fox
 */
public class Test {
    private volatile static int sum = 0;
    static Object object = "";
    static ReentrantLock lock = new ReentrantLock();

    static CASLock casLock = new CASLock();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                //synchronized (object) {
                //lock.lock();
                //
                for(;;){
                    //state=0
                    if(casLock.getState()==0&&casLock.cas()) {
                        try {
                            for (int j = 0; j < 10000; j++) {
                                sum++;
                            }
                            //
                            System.out.println(casLock.getState());
                        } finally {
                            //lock.unlock();
                            // state=0
                            casLock.setState(0);
                        }
                        break;
                    }
                }

                //}
            });
            thread.start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(sum);

    }


}
