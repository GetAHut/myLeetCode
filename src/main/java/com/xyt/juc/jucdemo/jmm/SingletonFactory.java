package com.xyt.juc.jucdemo.jmm;

/**
 * @author  Fox
 * hsdis-amd64.dll
 * 查看汇编指令
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp
 *  DCL为什么要使用volatile
 */
public class SingletonFactory {

    private static SingletonFactory myInstance;

    public static SingletonFactory getMyInstance() {
        if (myInstance == null) {
            synchronized (SingletonFactory.class) {
                if (myInstance == null) {
                    // 1. 开辟一片内存空间

                    // 3. myInstance指向内存空间的地址
                    // 2. 对象初始化
                    myInstance = new SingletonFactory();
                }
            }
        }
        return myInstance;
    }

    public static void main(String[] args) {
        SingletonFactory.getMyInstance();
    }
}
