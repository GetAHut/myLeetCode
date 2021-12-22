package com.xyt.juc.jucdemo.jmm;

public class VolatileTest2 {

    private static volatile boolean flag = true;

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (flag){
                        System.out.println("trun on");
                        flag = false;
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (!flag){
                        System.out.println("trun off");
                        flag = true;
                    }
                }
            }
        }).start();
    }
}