package com.xyt.leecode.designMode.adapter;

/**
 * @Author: xyt
 * @Description: 对象适配器
 * @Date: 2021/9/14 14:26
 */
public class ObjectAdapter implements Target {

    private Adaptee adaptee;

    @Override
    public int outPut() {
        adaptee = new Adaptee();
        int i = adaptee.outPutHigh();
        //......业务操作
        System.out.println(String.format("业务: %d 通过操作转换为-> 业务: %d", i, 5));
        return 5;
    }
}
