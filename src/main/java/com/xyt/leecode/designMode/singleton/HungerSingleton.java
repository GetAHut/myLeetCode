package com.xyt.leecode.designMode.singleton;

/**
 * @Author: xyt
 * @Description: 饿汉单例模式
 * @Date: 2021/9/2 16:06
 */
public class HungerSingleton {

    //基于JVM的类加载机制实现的。
    //类加载： 加载二进制数据到内存中，生存对应的Class数据结构
    //        连接： a 验证有效性， b 准备 （给静态成员赋值） c 解析
    //        初始化： 给类的静态变量赋初始值
    private static HungerSingleton instance = new HungerSingleton();
    //构造器初始化
    private HungerSingleton(){

    }

    public static HungerSingleton getInstance(){

        return instance;
    }

    public static void main(String[] args) {
        HungerSingleton instance = HungerSingleton.getInstance();
        HungerSingleton instance1 = HungerSingleton.getInstance();
        System.out.println(instance == instance1);
    }

}
