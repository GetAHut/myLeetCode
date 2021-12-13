package com.xyt.juc.jmm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author: xyt
 * @Date: 2021/12/9 23:06
 * @version: 1.0.0
 */
public class UnsafeFactory {

    /**
     * get UnSafe
     * @return
     */
    public static Unsafe getUnSafe(){
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return ((Unsafe) theUnsafe.get(null));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  get this field offset
     * @param unsafe
     * @param clazz
     * @param fieldName
     * @return
     */
    public static long getFieldOffset(Unsafe unsafe, Class clazz, String fieldName){
        try {
            return unsafe.objectFieldOffset(clazz.getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }
}
