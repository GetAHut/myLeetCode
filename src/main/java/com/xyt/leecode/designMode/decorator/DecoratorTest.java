package com.xyt.leecode.designMode.decorator;

/**
 * @Author: xyt
 * @Description: 装饰者模式 （实例参考Mybatis中的 cache-> 多层装饰着嵌套）
 * @Date: 2021/9/14 14:59
 */
public class DecoratorTest {

    /**
     * 模式定义：（开闭原则）
     *  在不改变原有对象的基础上，将功能附加到对象上
     */

    public static void main(String[] args) {
        Component photoGraph = new PhotoGraph();
        Component beauty = new Beauty(photoGraph);
        Component component = new FilterPhoto(beauty);
        component.operation();
    }
}

//设定一个拍照功能接口。
interface Component{
    void  operation();
}

//初始拍照功能
class PhotoGraph implements Component{

    @Override
    public void operation() {
        System.out.println("拍照...");
    }
}

//需要新添加功能， 且不能更改原有功能
abstract class ComponentDecorator implements Component{

    Component component;

    //通过构造器的模式传入
    public ComponentDecorator(Component component){
        this.component = component;
    }
}

//此时新增美颜功能
class Beauty extends ComponentDecorator{

    public Beauty(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("美颜...");
        component.operation();
    }
}

//新增 滤镜功能

class FilterPhoto extends ComponentDecorator{

    public FilterPhoto(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("滤镜...");
        component.operation();
    }
}


