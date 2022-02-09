package com.xyt.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: xyt
 * @Date: 2022/2/9 8:50
 * @version: 1.0.0
 */
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                // sleep方法清除中断标记
                Thread.sleep(5000);

                return "call method execute!";
            }
        });

        new Thread(task).start();
        // 阻塞等待线程运行结果出来，
        System.out.println(task.get());
    }
}
