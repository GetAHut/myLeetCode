package com.xyt.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: xyt
 * @Date: 2022/2/9 8:54
 * @version: 1.0.0
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "; future result";
        // 可以传入自定义线程池
        }).thenApply(r -> {
            return r + "thenApply called！";
        });

        try {
            // 通过get方法获取异步处理结果
            // future.join() 方法类似， 但是join无异常处理
            String s = future.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
