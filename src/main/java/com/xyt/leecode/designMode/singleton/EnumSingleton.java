package com.xyt.leecode.designMode.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: xyt
 * @Description:
 * @Date: 2021/9/2 16:48
 */
public enum EnumSingleton {
    INSTANCE;

    public void print(){
        System.out.println(this.hashCode());
    }
}

class EnumTest{

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        EnumSingleton instance = EnumSingleton.INSTANCE;
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        System.out.println(instance1 == instance); //true

        //通过反射
        Constructor<EnumSingleton> declaredConstructor =
                EnumSingleton.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        //报错 Cannot reflectively create enum objects
        EnumSingleton instance2 = declaredConstructor.newInstance("INSTANCE", 0);
        System.out.println(instance2 == instance);
    }
}
