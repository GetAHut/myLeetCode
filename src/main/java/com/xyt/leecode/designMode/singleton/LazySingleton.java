package com.xyt.leecode.designMode.singleton;

/**
 * @Author: xyt
 * @Description: 懒加载单例模式
 * @Date: 2021/9/2 16:07
 */
public class LazySingleton {

    //volatile 防止指令重排
    private volatile static LazySingleton instance;
    //构造器私有化
    private LazySingleton(){

    }

    /**
     * 双重判断
     * @return
     */
    public static LazySingleton getInstance(){
        if (instance == null) {
            synchronized (LazySingleton.class){
                if (instance == null){
                    instance = new LazySingleton();
                    //在字节码层面 会有指令重排，（2,3可以颠倒） 会先赋值 在实例化，
                    //1. 分配空间
                    //2. 初始化
                    //3. 引用赋值
                    //若先赋值 则在没有new就直接返回了  其实为空
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();
        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();
    }
}
