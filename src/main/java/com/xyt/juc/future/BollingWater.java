package com.xyt.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 烧开水问题
 *
 * @Author: xyt
 * @Date: 2022/2/9 9:41
 * @version: 1.0.0
 */
public class BollingWater {

    public static void main(String[] args) {
        // 任务1： 洗水壶-烧水
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1: 洗水壶！");
            sleep(1L, TimeUnit.SECONDS);

            System.out.println("T2: 烧开水！");
            sleep(5L, TimeUnit.SECONDS);
        });

        // 任务2： 洗茶杯-> 拿茶叶
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2: 洗茶杯");
            sleep(1L, TimeUnit.SECONDS);

            System.out.println("T2: 拿茶叶");
            sleep(1L, TimeUnit.SECONDS);

            return "龙井茶！";
        });

        // 任务3：将任务1和任务2合并后执行：泡茶
        CompletableFuture<String> future3 = future1.thenCombine(future2, (__, tf) -> {
            System.out.println("T1: 拿到茶叶：" + tf);
            System.out.println("T1: 泡茶....");
            return "上茶！";
        });

        // 等待任务3获取结果
        System.out.println(future3.join());
    }



    public static void sleep(Long millis, TimeUnit unit){
        try {
            unit.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
