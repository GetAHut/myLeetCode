package com.xyt.leecode.buildModel;

/**
 * 创建ConcreteBuilder 用以实现PersonBuild接口
 *
 * @Author: abby
 * @Date: 2020/6/1 11:38
 */
public class PersonConcreteBuilder implements PersonBuild {

    private Person person;

    public PersonConcreteBuilder(){
        person = new Person();
    }

    @Override
    public void buildHead() {
        person.setHead("创建head");
    }

    @Override
    public void buildBody() {
        person.setBody("创建body");
    }

    @Override
    public void buildFoot() {
        person.setFoot("创建foot");
    }

    @Override
    public Person buildPerson() {
        return person;
    }
}
