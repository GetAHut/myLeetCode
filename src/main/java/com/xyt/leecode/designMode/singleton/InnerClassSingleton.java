package com.xyt.leecode.designMode.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * @Author: xyt
 * @Description: 静态内部类的单例模式
 * @Date: 2021/9/2 16:22
 */
public class InnerClassSingleton implements Serializable {

    //序列化版本
    static final long serialVersionUID = 42L;

    //构造一个静态的私有的内部类
    //静态内部类的初始化 是在 调用getInstance()时 才会去初始化 内部类。
    private static class InnerClassHolder{
        //静态内部类持有实例
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    //私有构造器
    private InnerClassSingleton(){
        //避免被通过反射获取实例
        if (InnerClassHolder.instance != null){
            throw new RuntimeException("单例不允许多个实例！");
        }
    }

    public static InnerClassSingleton getInstance(){
        return InnerClassHolder.instance;
    }

    /**
     * 实现此方法 用来表示在反序列化时返回什么样的值
     * 这里需要返回实例过的对象
     * @return
     * @throws ObjectStreamException
     */
    Object readResolve() throws ObjectStreamException{
        return InnerClassHolder.instance;
    }


}

class InnerClassTest{
    public static void main(String[] args) {
        InnerClassSingleton instance = InnerClassSingleton.getInstance();
        InnerClassSingleton instance1 = InnerClassSingleton.getInstance();
        System.out.println(instance == instance1);

    }
}
