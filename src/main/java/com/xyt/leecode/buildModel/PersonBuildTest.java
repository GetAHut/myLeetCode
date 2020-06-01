package com.xyt.leecode.buildModel;

/**
 * 建造者模式测试
 *
 * @Author: abby
 * @Date: 2020/6/1 12:16
 */
public class PersonBuildTest {

    public static void main(String[] args) {
        PersonDirector pd = new PersonDirector();
        Person person = pd.creatPerson(new PersonConcreteBuilder());
        System.out.println(person.getBody());
        System.out.println(person.getHead());
        System.out.println(person.getFoot());
    }
}
