package com.xyt.leecode.designMode.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;

/**
 * @Author: xyt
 * @Description:    原型模式
 * @Date: 2021/9/7 18:07
 */
public class PrototypeTest {

    /**
     * 原型模式: 指原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。
     *  拷贝：浅拷贝、深拷贝 Object#clone()方法 需要实现Cloneable接口 clone()方法才可以使用
     *      浅拷贝： 使用clone()方法 拷贝非静态字段拷贝到新对象，如果存在引用类型，
     *              则只拷贝引用类型的值。
     *      深拷贝： 就是在浅拷贝基础上将引用类型的属性也重新拷贝。（深拷贝 重新开辟空间新建对象）
     */

    public static void main(String[] args) throws CloneNotSupportedException {
//        User name = new User("name", "123", 123);
//        User clone = name.clone();
//        //返回两个不同的对象， 浅拷贝已经成功了
//        //original:1229416514 ] User{username='name', phone='123', salary=123}
//        //clone:2016447921 ] User{username='name', phone='123', salary=123}
//        System.out.println("original:" + name);
//        System.out.println("clone:" + clone);

        //若对象中有引用类型属性
        School school = new School("school", "132", 12333);
        User name = new User("name", "123", 123, school);
        User clone = name.clone();
        //original:1229416514 User{username='name', phone='123', salary=123,
        // school=2016447921 ] School{classname='school', area='132', age=12333}}
        //clone:666988784 User{username='name', phone='123', salary=123,
        // school=2016447921 ] School{classname='school', area='132', age=12333}}
        //可以看到School对象是一致的 ，如果School发生修改 则会同步修改
        System.out.println("original:" + name);
        System.out.println("clone:" + clone);


    }

}

@Data
@AllArgsConstructor
class User implements Cloneable, Serializable {

    private static final long serialVersionUID = 42L;

    private String username;
    private String phone;
    private Integer salary;
    private School school;

    //....表示复杂对象


    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected User clone() throws CloneNotSupportedException {
        //如果User中存在了例如School一样的引用类型浅拷贝则不能改变School
        //因此 School也需要实现Cloneable接口，且重写clone方法
        //在属性中其重新赋值 这样可以改变引用对象
        //缺点在于引用对象过多 因此可以使用序列化的方式来实现
//        User user = ((User) super.clone());
//        School clone = this.school.clone();
//        user.setSchool(clone);
//        //此处强转则不需要在外面强转
//        return user;

        //使用序列化的方式实现
        //这样引用类型 同样被拷贝 （深拷贝）
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();
        //写入内存中
        try {
            oos = new ObjectOutputStream(byteArrayOutputStream);
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取对象
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            User o = ((User) ois.readObject());
            return o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString() {
        return super.hashCode() + " User{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", school=" + school +
                '}';
    }
}

@Data
@AllArgsConstructor
class School implements Cloneable, Serializable{

    private static final long serialVersionUID = 42L;

    private String classname;
    private String area;
    private Integer age;

    @Override
    protected School clone() throws CloneNotSupportedException {
        return (School)super.clone();
    }

    @Override
    public String toString() {
        return super.hashCode() + " ] School{" +
                "classname='" + classname + '\'' +
                ", area='" + area + '\'' +
                ", age=" + age +
                '}';
    }
}
