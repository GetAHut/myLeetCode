package com.xyt.juc.jucdemo.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fox
 */
@Slf4j
public class ABATest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);

        new Thread(()->{
            int value = atomicInteger.get();
            log.debug("Thread1 read value: " + value);

            // 阻塞1s
            LockSupport.parkNanos(1000000000L);

            // Thread1通过CAS修改value值为3
            if (atomicInteger.compareAndSet(value, 3)) {
                log.debug("Thread1 update from " + value + " to 3");
            } else {
                log.debug("Thread1 update fail!");
            }
        },"Thread1").start();

        new Thread(()->{
            int value = atomicInteger.get();
            log.debug("Thread2 read value: " + value);
            // Thread2通过CAS修改value值为2
            if (atomicInteger.compareAndSet(value, 2)) {
                log.debug("Thread2 update from " + value + " to 2");

                // do something
                value = atomicInteger.get();
                log.debug("Thread2 read value: " + value);
                // Thread2通过CAS修改value值为1
                if (atomicInteger.compareAndSet(value, 1)) {
                    log.debug("Thread2 update from " + value + " to 1");
                }
            }
        },"Thread2").start();
    }
}
