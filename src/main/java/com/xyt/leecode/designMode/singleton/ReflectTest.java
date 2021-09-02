package com.xyt.leecode.designMode.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: xyt
 * @Description: 反射单例测试
 * @Date: 2021/9/2 16:31
 */
public class ReflectTest {

    /**
     * 对单例模式  进行反射获取实例测试
     *  得出的结果与getInstance()获取的实例是不相等的
     */

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        //以测试InnerClassSingleton为例
//        Constructor<InnerClassSingleton> declaredConstructor =
//                InnerClassSingleton.class.getDeclaredConstructor();
//        //私有构造器设置为可访问
//        declaredConstructor.setAccessible(true);
//        //通过反射拿到实例
//        InnerClassSingleton innerClassSingleton = declaredConstructor.newInstance();
//        //通过既有的单例模式获取
//        InnerClassSingleton instance = InnerClassSingleton.getInstance();
//        //推翻了单例模式单一实例原则
//        //newInstance() 会排除enum方式 所以枚举单例模式可以天然避开被反射创建
//        System.out.println(instance == innerClassSingleton);    //false



        //序列化测试 需要类实现Serializable接口
        InnerClassSingleton instance1 = InnerClassSingleton.getInstance();

//        //写入到磁盘文件
//        ObjectOutputStream oos =
//                new ObjectOutputStream(new FileOutputStream("testSerializable"));
//        oos.writeObject(instance1);
//        oos.close();

        //将写入的文件反序列化
        ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream("testSerializable"));
        InnerClassSingleton o = ((InnerClassSingleton) ois.readObject());

        //序列化 反序列化 拿到的实例同样不符合单例模式
        //解决办法：实现Object readResolve() throws ObjectStreamException;
        //注意 序列化一定要加版本号
        System.out.println(instance1 == o);     //  false 实现readResolve()后返回true

    }
}
