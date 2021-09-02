package com.xyt.leecode.designMode.factory;

/**
 * @Author: xyt
 * @Description: 工厂方法模式
 * @Date: 2021/9/2 15:48
 */
public class FactoryDemo{

    public static void main(String[] args) {
        ProductFactory factory = new ConCreateProductA();
        Product product = factory.getObject();
        product.sell();
    }


}

abstract class ProductFactory {

    //
    abstract Product createProduct();

    Product getObject(){
        Product product = createProduct();
        //...
        return product;
    }
}

/**
 * 定义产品 通用接口
 */
interface Product{

    void sell();
}

//各类产品
class ProductA implements Product{

    @Override
    public void sell() {
        //.....
        System.out.println("product A");
    }
}

//各类产品
class ProductB implements Product{
    @Override
    public void sell() {
        //...
        System.out.println("product B");
    }
}

//单一职责原则
class ConCreateProductA extends ProductFactory{

    @Override
    Product createProduct() {
        return new ProductA();
    }
}

class ConCreateProductB extends ProductFactory{

    @Override
    Product createProduct() {
        return new ProductB();
    }
}
