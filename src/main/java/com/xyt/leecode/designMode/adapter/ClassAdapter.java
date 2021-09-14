package com.xyt.leecode.designMode.adapter;

/**
 * @Author: xyt
 * @Description: 类的适配器
 * @Date: 2021/9/14 14:27
 */
public class ClassAdapter extends Adaptee implements Target {

    //通过继承的方式

    @Override
    public int outPut() {
        //继承可以直接拿到父类方法
        int i = outPutHigh();
        //......业务操作
        System.out.println(String.format("业务: %d 通过操作转换为-> 业务: %d", i, 5));

        return 5;
    }
}
