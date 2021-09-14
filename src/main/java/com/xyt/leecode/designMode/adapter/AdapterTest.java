package com.xyt.leecode.designMode.adapter;

/**
 * @Author: xyt
 * @Description: 适配器模式
 * @Date: 2021/9/14 14:20
 */
public class AdapterTest {

    /**
     * 适配器有两种模式
     *  适配器是将一个类的接口，转换成客户希望的另一个接口。
     *  Adapter模式使得原本由于接口不兼容而不能一起工作的类一起工作。
     *  1. 对象适配器模式，
     *  2. 类的适配器模式
     *      此模式方法都会获取到， 这样不符合单一原则。可能会产生污染。
     *
     */

    public static void main(String[] args) {

        Target classAdapter = new ClassAdapter();
        classAdapter.outPut();
        Target objectAdapte = new ObjectAdapter();
        objectAdapte.outPut();

    }
}
