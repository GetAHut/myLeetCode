package com.xyt.leecode.designMode.factory;

/**
 * @Author: xyt
 * @Description: 简单工厂  非设计模式
 * @Date: 2021/9/2 16:00
 */
public class SimpleFactoryDemo {

    public static void main(String[] args) {
        SimpleFactory factory = new SimpleFactory();
        Car a = factory.create("A");
        a.sell();
    }
}

//简单工厂
class SimpleFactory{

    public Car create(String type){
        if (type.equals("A")){
            return new CarA();
        } else if (type.equals("B")){
            return new CarB();
        } else
            return null;
    }
}

interface Car{

    void sell();
}

class CarA implements Car{
    @Override
    public void sell() {
        System.out.println("car A");
    }
}

class CarB implements Car{
    @Override
    public void sell() {
        System.out.println("car B");
    }
}
